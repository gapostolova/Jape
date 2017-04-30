package com.example.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import com.example.model.User;
import com.example.model.Gag;
import com.example.model.Category;
import com.example.model.Comment;


public class GagDAO {

	private static final int MIN_TRENDING_GAG_UPVOTES = 500;
	private static final int MIN_HOT_GAG_UPVOTES = 2000;
	private static TreeMap<Long, Gag> allGags;
	private static GagDAO instance;
	private	Connection conn = DBManager.getInstance().getConnection();
	
	private GagDAO () {
		allGags = new TreeMap<>();
	}
	
	public static synchronized GagDAO getInstance() {
		if(instance == null)
			instance = new GagDAO();
		return instance;
	}
	
	public synchronized Map<Long, Gag> getAllGags() throws SQLException {
		
		try {
			Map<String, User> users = UserDAO.getInstance().getAllUsers();
			for(User user : users.values()){
				Map<Long, Gag> gags =  user.getGags();
				allGags.putAll(gags);
			}
			
		} catch (SQLException e) {
			System.out.println("Could not get users from UserDAO in GagDAO: "+ e.getMessage());
			throw e;
		}
		
		return Collections.unmodifiableMap(allGags);
	}
	
	public void addGag(Gag gag) throws SQLException{
		try {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO `9gag`.`gags` (`content`, `nsfw`, `title`, `points`, `user_id`, `public`, `type`) VALUES (?,?,?,?,?,?,?);";
			
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, gag.getGag());
			pst.setBoolean(2, gag.isNsfw());
			pst.setString(3, gag.getTitle());
			pst.setInt(4, gag.getUpvotes());
			pst.setLong(5, gag.getUserId());
			pst.setBoolean(6, gag.isPublic());
			pst.setString(7, gag.getType());
			pst.executeUpdate();
			
			//add gagId
		    ResultSet res = pst.getGeneratedKeys();
			res.next();
			long gagId = res.getLong(1);
			gag.setGagID(gagId);
			System.out.println("GAG V GAG DAO");
			System.out.println(gag);
			//add gags' categories ;(
			for(Category cat : gag.getCategory()){
				
				sql = "INSERT INTO `9gag`.`gags_in_categories` (`gags_gag_id`, `categories_category_id`) VALUES (?, ?);";
				pst = conn.prepareStatement(sql);
				pst.setLong(1, gag.getGagID());
				pst.setLong(2, cat.getCategoryID());
				pst.executeUpdate();
			}
			
			UserDAO.getInstance().addGagToUser(gag);
		} catch (SQLException e) {
			System.out.println("GagDAO exception!");
			conn.rollback();
		} finally {
			conn.setAutoCommit(true);
		}
		
	}
	
	
	
	public List<Gag> allCommentedGags(long userId){
		TreeSet<Gag> commentedGags = new TreeSet<>();
		for(long gagId: allGags.keySet()){
			Gag gag = allGags.get(gagId);
			List<Comment> comments = gag.getComments();
			
				for(Comment comment: comments){
					if(comment.getUserId() == userId)
						commentedGags.add(gag);
				}
			
		}
		ArrayList<Gag> comGagList= new ArrayList<>();
		comGagList.addAll(commentedGags);
		return Collections.unmodifiableList(comGagList);
	}
	
	
	public List<Gag> hotGags(){
		//TreeMap<Long, Gag> hot = new TreeMap<>();
		ArrayList<Gag> hot = new ArrayList<>();
		for(Gag gag : allGags.values()){
			if(gag.getUpvotes() >= MIN_HOT_GAG_UPVOTES){
				//hot.put(gag.getGagID(), gag);
				hot.add(gag);
			}
		}
		//return Collections.unmodifiableMap(hot);
		return Collections.unmodifiableList(hot);
	}
	
	public List<Gag> trendingGags(){
		//TreeMap<Long, Gag> trending = new TreeMap<>();
		ArrayList<Gag> trending = new ArrayList<>();
		
		for(Gag gag : allGags.values()){
			if(gag.getUpvotes() < MIN_HOT_GAG_UPVOTES && gag.getUpvotes() >= MIN_TRENDING_GAG_UPVOTES){
				//trending.put(gag.getGagID(), gag);
				trending.add(gag);
			}
		}
		//return Collections.unmodifiableMap(trending);
		return Collections.unmodifiableList(trending);
	}
	
	
	public List<Gag> freshGags(){
		//TreeMap<Long, Gag> fresh = new TreeMap<>();
		ArrayList<Gag> fresh = new ArrayList<>();
				
		for(Gag gag : allGags.values()){
			if(gag.getUpvotes() < MIN_TRENDING_GAG_UPVOTES){
				//fresh.put(gag.getGagID(), gag);
				fresh.add(gag);
			}
		}
		//return Collections.unmodifiableMap(fresh);
		return Collections.unmodifiableList(fresh);
	}
	
	public List<Gag> categoryGags(String category) {
		ArrayList<Gag> gags = new ArrayList<>();
		
		for(Gag gag : allGags.values()) {
			if(gag.containsCategory(category)) {
				gags.add(gag);
			}
		}
		
		return Collections.unmodifiableList(gags);
		
	} 
	
	public void deleteComment(Comment c) {
		
		Gag gagTemp = null;
		try {
			for(Gag gag : UserDAO.getInstance().getAllUsers().get(UserDAO.getInstance().getUserEmail(c.getUserId())).getGags().values()) {
				if(gag.getGagID() == c.getGagId()) {
					gagTemp = gag;
					break;
				}	
			}
			gagTemp.deleteComment(c);
		} catch (SQLException e) {
			System.out.println("Comment not added to gag");
		}
	}
	
	
	public Gag getGagById(long gagId){
		for(Gag gag : allGags.values()){
			if(gag.getGagID() == gagId)
				return gag;
		}
		return null;
		
	}
	
	//working and tested - deletes gag, deletes all comments assosiated with it
	public synchronized void deleteGag(Gag gag) throws SQLException {
		try {
			conn.setAutoCommit(false);
			//delete comments assosiated with this gag
			CommentDAO.getInstance().deleteGagComments(gag);
			
			//delete from collection
			
			gag.deleteAllComments();
			
			//delete gag
			
			String sql = "DELETE FROM 9gag.gags WHERE gag_id=?;";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setLong(1, gag.getGagID());
			
			pst.executeUpdate();
			
			PreparedStatement pstLikes = conn.prepareStatement("DELETE FROM 9gag.liked_gags WHERE gag_id = ?;");
			pstLikes.setLong(1, gag.getGagID());
			
			pstLikes.executeUpdate();
			
			conn.commit();
			
			} catch (SQLException e) {
				conn.rollback();
				System.out.println("Could not delete gag in GagDAO: " + e.getMessage());
				throw e;
			} finally {
				conn.setAutoCommit(true);
			}
		
	}
	
}
