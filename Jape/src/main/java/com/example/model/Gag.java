package com.example.model;


import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;


import com.example.model.dao.UserDAO;

public class Gag implements Comparable<Gag> {
	
	private static final int DEFAULT_GAG_ID = -1;
	private String gag;
	private String title;
	private long userId;
	private long gagID;
	private boolean nsfw;
	private ArrayList<Category> category;
	private int upvotes;
	private boolean isPublic;
	private TreeSet<Comment> comments;
	private String type;
	
	
	//constructor for when a gag is added
	public Gag(String gag, String title, long userId, boolean nsfw, boolean isPublic, String type) {
		if(gag != null && !gag.isEmpty()){
			this.gag = gag;
		}
		if(title != null && !title.isEmpty()){
			this.title = title;
		}
		this.userId = userId;
		this.nsfw = nsfw;
		this.comments = new TreeSet<Comment>();
		this.isPublic = isPublic;
		
		if(type != null && !type.isEmpty()){
			this.type = type;
		}
		this.upvotes = 0;
		this.category = new ArrayList<>();
		this.gagID = DEFAULT_GAG_ID;
		
	}
	

	public Gag(String gag, String title, long userId, long gagID, boolean nsfw, boolean isPublic, String type) {
		if(gag != null && !gag.isEmpty()){
			this.gag = gag;
		}
		if(title != null && !title.isEmpty()){
			this.title = title;
		}
		this.userId = userId;
		this.gagID = gagID;
		this.nsfw = nsfw;
		
		this.comments = new TreeSet<Comment>();
		this.isPublic = isPublic;
		
		if(type != null && !type.isEmpty()){
			this.type = type;
		}
		this.category = new ArrayList<>();
	}
	
	public Gag(String gag, String title, long userId, boolean nsfw, boolean isPublic) {
		this(gag, title, userId, nsfw, isPublic, "gabi"); 
	
	}
	
	public List<Comment> motherShips(){
		ArrayList<Comment> children = new ArrayList<>();
		for(Comment c : comments){
			 if(c.getMotherCommentId() == 0){
				 children.add(c);
			 }
		}
		return Collections.unmodifiableList(children);
	}
	
	public List<Comment> commentsOfMother(long mothershipId){
		ArrayList<Comment> children = new ArrayList<>();
		for(Comment c : comments){
			 if(c.getMotherCommentId() == mothershipId){
				 children.add(c);
			 }
		}
		return Collections.unmodifiableList(children);
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void Upvote(){
		this.upvotes++;
	}
	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}
	
	public int getUpvotes() {
		return upvotes;
	}
	
	public void Downvote(){
		this.upvotes--;
	}
	
	public void addComment(Comment comment){
		this.comments.add(comment);
	}
	
	public void setComments(TreeSet<Comment> comments) {
		this.comments = comments;
	}
	
	
	public void addCategory(Category c){
		this.category.add(c);
	}
	
	public boolean hasCategory(String categor){
		for(Category cat : category){
			if(cat.getCategoryName().equals(categor.trim().toUpperCase())){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Category> getCategory() {
		return category;
	}
	
	public long getGagID() {
		return gagID;
	}
	
	public String getGag() {
		return gag;
	}
	
	public boolean isNsfw() {
		return nsfw;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getType() {
		return type;
	}
	
	public void setGagID(long gagID) {
		this.gagID = gagID;
	}
	
	public boolean isPublic() {
		return isPublic;
	}
	
	public void setCategory(ArrayList<Category> category) {
		this.category = category;
	}
	
	
	
	@Override
	public int compareTo(Gag g) {
		return (int) (g.gagID-this.gagID);
	}

	@Override
	public String toString() {
		return "	Gag [gag=" + gag + ", title=" + title + ", userId=" + userId + ", gagID=" + gagID + "]\n";
	}
	
	public void deleteComment(Comment comment) {
		Iterator<Comment> it = this.comments.iterator();
		while(it.hasNext()) {
			if(it.next().getMotherCommentId() == comment.getCommentId())
				it.remove();
		}
		this.comments.remove(comment);
	}
	
public String userName(){
		
		try {
			return UserDAO.getInstance().getUser(UserDAO.getInstance().getUserEmail(userId)).getUsername();
		} catch (SQLException e) {
			System.out.println(" could not get userName: "+ e.getMessage());
		}
		return "no name";
		
	}
	
	public void deleteAllComments() {
		this.comments = null;
	}
	
	public List<Comment> getComments() {
		ArrayList<Comment> cmnt = new ArrayList<>();
		cmnt.addAll(this.comments);
		
		return Collections.unmodifiableList(cmnt);
	}
	
	public String getGagIDString() {
		return Long.toString(this.gagID);
	}
	
	public String getTitleLower() {
		return this.title.toLowerCase();
	}
	
	public boolean containsCategory(String catName) {
		for(Category cat : category) {
			if(cat.getCategoryName().equalsIgnoreCase(catName))
				return true;
		}
		return false;
	}


	//hashcode by gagId - used in search
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (gagID ^ (gagID >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gag other = (Gag) obj;
		if (gagID != other.gagID)
			return false;
		return true;
	}
}
