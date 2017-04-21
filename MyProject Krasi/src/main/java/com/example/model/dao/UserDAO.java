package com.example.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import com.example.model.User;
import com.example.model.Category;
import com.example.model.Comment;
import com.example.model.Gag;




public class UserDAO {

	//							   	Email, User
	private static HashMap<String, User> allUsers = new HashMap<>();
	private	Connection conn = DBManager.getInstance().getConnection();
	private boolean dataHasChanged = false;
	private static UserDAO instance;
	
	private UserDAO() {}
	
	public static synchronized UserDAO getInstance() {
		if(instance == null){
			instance = new UserDAO();
		}
		return instance;
	}
	
	public void setDataHasChanged(boolean dataHasChanged) {
		this.dataHasChanged = dataHasChanged;
	}
	
	public void addUserInCollection(User user){
		allUsers.put(user.getEmail(), user);
		
	}
	
	
	
	public synchronized Map<String , User> getAllUsers() throws SQLException{
		
		if(allUsers.isEmpty() || dataHasChanged == true){
			
			String sql = "SELECT u.user_id, u.username, u.password, u.email, u.nsfw, u.profile_pic, u.gender, u.birthday, u.description, u.admin, u.is_verified, u.verification_key from users u;";
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while(res.next()){
				
			
				User user = new User(res.getString("username"), res.getString("email"), res.getString("password"), res.getInt("user_id"), res.getBoolean("nsfw"), res.getString("profile_pic"), res.getString("gender"),res.getDate("birthday").toLocalDate(), res.getString("description"), res.getBoolean("admin"), res.getBoolean("is_verified"), res.getString("verification_key"));
				//add gags/videos and comments
				user.setGags(usersGags(user.getUserId()));
				
				
				
				allUsers.put(user.getEmail(), user);
			}
		}
		dataHasChanged = false;
		return Collections.unmodifiableMap(allUsers);
	}
	
	
	//get all gags of user with id : userId
	private synchronized TreeSet<Gag> usersGags(long userId) throws SQLException{
		
		TreeSet<Gag> gags = new TreeSet<Gag>();
		String sql = "SELECT gag_id, content, nsfw, title, points, public, type, user_id FROM gags WHERE user_id = " + userId + ";";
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet res = st.executeQuery();
		
		while(res.next()){
			//add list of categories
			Gag gag = new Gag(res.getString("content"), res.getString("title"), res.getLong("user_id"), res.getLong("gag_id"), res.getBoolean("nsfw"), res.getBoolean("public"), res.getString("type"));
			gag.setUpvotes(res.getInt("points"));
			gag.setCategory(categories(gag.getGagID()));
			TreeSet<Comment> comments = comments(gag.getGagID());
			gag.setComments(comments);	
			//fill comments
			CommentDAO.getInstance().addComments(comments);
			gags.add(gag);
		}
		return gags;
	}
	
	
	
	private synchronized TreeSet<Comment> comments(long gagId) throws SQLException{
		
		TreeSet<Comment> cmnts = new TreeSet<>();
		
		String sql = "select comment_id, time, description, mothership_id, points, user_id, gag_id from comments where gag_id =" + gagId +";";
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet res;
		
			res = st.executeQuery();
		
		while(res.next()){
			java.sql.Timestamp date = res.getTimestamp("time");
			Comment comment = new Comment(res.getInt("user_id"), res.getInt("gag_id"), res.getInt("comment_id"), date.toLocalDateTime(), res.getString("description"), res.getLong("mothership_id"));
			comment.setUpvotes(res.getInt("points"));
			cmnts.add(comment);
		}
		
		return cmnts;
	}
	

	public synchronized User getUser(String email) throws SQLException{
		
		if(getAllUsers().containsKey(email)){
			return getAllUsers().get(email);
		}
		return null;
	}
	
	private synchronized ArrayList<Category> categories(long gagID) throws SQLException{	
		ArrayList<Category> category = new ArrayList<>();
		String sql = "select categories_category_id, name from gags_in_categories join categories on(categories_category_id = category_id) where gags_gag_id=" + gagID;
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet res = st.executeQuery();
		while(res.next()){
			Category cat = new Category(res.getLong("categories_category_id"), res.getString("name"));
			category.add(cat);
		}
		return category;
		

	}
	
	
	public synchronized boolean isVerified(String email) throws SQLException {
		if(exists(email)){
			return getAllUsers().get(email).isVerified();
		}
		return false;
	}

	public synchronized boolean verify(String email, String verificationKey) throws SQLException {
		if(exists(email)){
			User user = getAllUsers().get(email);
			user.verify(verificationKey);
			String sql = "UPDATE users SET is_verified='1' WHERE email=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, email);
			st.executeUpdate();
			return getAllUsers().get(email).isVerified();
		}
		return false;
	}

	public synchronized boolean exists(String email) throws SQLException {
		return getAllUsers().containsKey(email);
	}
	
	
	public String getUserEmail(long userId) {
		String email = null;
		try {
			
			String sql = "select email from users where user_id = "+userId + ";";
			PreparedStatement pst;
			
				pst = conn.prepareStatement(sql);
			
			ResultSet res = pst.executeQuery();
			while(res.next()){
				email = res.getString("email");
			}
		} catch (SQLException e) {
			System.out.println("getUserEmail(long userId) in UserDAO: " + e.getMessage());
		}
		return email;	
	}

	
	public void addGagToUser(Gag gag) {
		
		System.out.println("ADD GAG TO USER in USER DAO");
		System.out.println(gag);
		String email = getUserEmail(gag.getUserId());
		User user = allUsers.get(email);
		user.addGag(gag);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	//add user --- whole process should be a transaction
	
	public void addUser(User u) {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement(
				"INSERT INTO users (username, password, email, nsfw, profile_pic, gender, birthday, country, description, admin) VALUES (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, u.getUsername());
			pst.setString(2, u.getPassword());
			pst.setString(3, u.getEmail());
			pst.setBoolean(4, u.isViewNsfwContent());
			pst.setString(5, u.getProfilePic());
			pst.setString(6, u.getGender());
			pst.setDate(7, java.sql.Date.valueOf(u.getDateOfBirth()));
			pst.setString(9, u.getDescription());
			pst.setBoolean(10, u.isAdmin());
			pst.executeUpdate();
			
			//add id
		    ResultSet res = pst.getGeneratedKeys();
			res.next();
			long id = res.getLong(1);
			u.setUserId(id);
			res.close();
		    System.out.println("User inserted successfuly into DB");
		    
		} catch (SQLException e) {
			System.out.println("Couldn't insert user into DB.");
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println("Couldn't close prepared statement");
			}
		}
	}
	
		//delete user
	
	public synchronized void deleteUser(User u) {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement(
					"DELETE FROM TABLE users WHERE user_id = ?");
			pst.setLong(1, u.getUserId());
			pst.executeUpdate();
			System.out.println("User successfuly deleted.");
		} catch (SQLException e) {
			System.out.println("Couldn't delete user.");
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println("Couldn't close prepared statement.");
			}
		}
	}
	
	//all of the methods above should be validated in the UserManager
	
		//change password
	
	public synchronized void changePass(String pass, User u) {
		
		PreparedStatement pst = null;
		
		try {
			pst =conn.prepareStatement(
					"UPDATE users SET password = ? WHERE user_id = ?;");
			pst.setString(1, pass);
			pst.setLong(2, u.getUserId());
			pst.executeUpdate();
			System.out.println("Updated user password.");	
		} catch (SQLException e) {
			System.out.println("Couldn't update password.");
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println("Couldn't close prepared statement.");
			}
		}
		
	}
	
		//change profile pic
	
	public synchronized void changePic(String pic, User u) {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement(
					"UPDATE users SET profile_pic = ? WHERE user_id = ?;");
			pst.setString(1, pic);
			pst.setLong(2, u.getUserId());
			pst.executeUpdate();
			System.out.println("Updated user profile pic.");	
		} catch (SQLException e) {
			System.out.println("Couldn't update pic.");
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println("Couldn't close prepared statement.");
			}
		}
	}
	
		//change nsfw status
	
	public synchronized void changeNSFW(boolean nsfw, User u) {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement(
					"UPDATE users SET nsfw = ? WHERE user_id = ?;");
			pst.setBoolean(1, nsfw);
			pst.setLong(2, u.getUserId());
			pst.executeUpdate();
			System.out.println("Updated user nsfw status.");	
		} catch (SQLException e) {
			System.out.println("Couldn't update nsfw status.");
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println("Couldn't close prepared statement.");
			}
		}
	}
	
		//change description
	
	public synchronized void changeDescription(String description, User u) {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement(
					"UPDATE users SET description = ? WHERE user_id = ?;");
			pst.setString(1, description);
			pst.setLong(2, u.getUserId());
			pst.executeUpdate();
			System.out.println("Updated user description.");	
		} catch (SQLException e) {
			System.out.println("Couldn't update user description.");
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println("Couldn't close prepared statement.");
			}
		}
	}
		//change admin status
	
	public synchronized void changeAdmin(boolean admin, User u) {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement(
					"UPDATE users SET admin = ? WHERE user_id = ?;");
			pst.setBoolean(1, admin);
			pst.setLong(2, u.getUserId());
			pst.executeUpdate();
			System.out.println("Updated admin status.");	
		} catch (SQLException e) {
			System.out.println("Couldn't update admin status.");
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println("Couldn't close prepared statement.");
			}
		}
	}

	
		
}
