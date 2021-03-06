package com.example.model;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.TreeSet;

import com.example.model.dao.UserDAO;
import com.mysql.fabric.xmlrpc.base.Array;



public class Comment implements Comparable<Comment> {
	
	private static final int DEFAULT_UPVOTES = 0;
	private  long userId;
	private long gagId;
	private long commentId;
	private LocalDateTime date;
	private String content;
	private long motherCommentId;
	private int upvotes;
	private User user;
	
	//?? private TreeSet<Comment> replies;
	//				komentirasht, gag, commentara
	public Comment(long userId, long gagId, long commentId, LocalDateTime date, String content, long motherCommentId) {
		super();
		this.userId = userId;
		this.gagId = gagId;
		this.commentId = commentId;
		this.date = date;
		if(content != null && !content.isEmpty()){
			this.content = content;
		}
		this.motherCommentId = motherCommentId;
		this.upvotes = DEFAULT_UPVOTES;
		
	}
	
	public String userName(){
		
		try {
			return UserDAO.getInstance().getUser(UserDAO.getInstance().getUserEmail(userId)).getUsername();
		} catch (SQLException e) {
			System.out.println(" could not get userName: "+ e.getMessage());
		}
		return "no name";
		
	}
	
	public String getProfilePicName(){
	
		try {
			String pic= UserDAO.getInstance().getUser(UserDAO.getInstance().getUserEmail(userId)).getProfilePic();
			String[] name = pic.split("\\.");
//			System.out.println("=========================================="+pic);
//			System.out.println(Arrays.toString(name));
			return name[0];
		} catch (SQLException e) {
			System.out.println(" could not get userName: "+ e.getMessage());
		}
		
		return "defaultProfilePic";
	}
	public String getProfilePicType(){
		
		try {
			String pic =  UserDAO.getInstance().getUser(UserDAO.getInstance().getUserEmail(userId)).getProfilePic();
			String[] name = pic.split("\\.");
			System.out.println("=========================================="+pic);
			System.out.println(Arrays.toString(name));
//			System.out.println(name[1]);
			return name[1];
		} catch (SQLException e) {
			System.out.println(" could not get userName: "+ e.getMessage());
		}
		
		
		return "jpg";
	}
	
	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}
	
	public void Upvote(){
		this.upvotes++;
	}
	
	public void Downvote(){
		this.upvotes--;
	}

	@Override
	public int compareTo(Comment o) {
		return (int) (this.commentId - o.commentId);
	}

	@Override
	public String toString() {
		return "Comment [userId=" + userId + ", gagId=" + gagId + ", commentId=" + commentId + ", date=" + date
				+ ", content=" + content + ", motherCommentId=" + motherCommentId + ", upvotes=" + upvotes + "]\n";
	}
	
//  this returns user email of the person that makes the comment
//	public String getUserEmail() throws SQLException {
//		String email = null;
//		for(User u : UserDAO.getInstance().getAllUsers().values()) {
//			if(this.userId == u.getUserId()) {
//				email = u.getEmail();
//				break;
//			}
//		}
//		 return email;	
//	}
//	
	public long getGagId() {
		return this.gagId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getMotherCommentId() {
		return motherCommentId;
	}

	public void setMotherCommentId(long motherCommentId) {
		this.motherCommentId = motherCommentId;
	}
	
	public int getUpvotes() {
		return this.upvotes;
	}
	

}
