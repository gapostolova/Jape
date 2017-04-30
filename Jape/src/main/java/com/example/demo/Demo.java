package com.example.demo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.example.model.User;
import com.example.model.Category;
import com.example.model.Comment;
import com.example.model.Gag;

import com.example.model.dao.CommentDAO;
import com.example.model.dao.UserDAO;
import com.example.model.dao.GagDAO;;




public class Demo {
	
	public static void main(String[] args) throws SQLException {
	
		
		Map<String, User> users = UserDAO.getInstance().getAllUsers();
		
		System.out.println("=====================		USERS		======================\n");	
		System.out.println(users);
		
		System.out.println("=====================		Comments		======================\n");	
		System.out.println(CommentDAO.getInstance().allComments);
		
		
	Map<Long, Gag> gags = GagDAO.getInstance().getAllGags();
	System.out.println(gags);
	
//	Gag gag = new Gag("smeshnichko", "Dano da stane", 22, true, true, "jpg");
//	gag.setUpvotes(600);
//	gag.addCategory(new Category(1, "funny"));
//	gag.addCategory(new Category(2, "savage"));
//	GagDAO.getInstance().addGag(gag);
	

//	Comment comment = new Comment(5, 7, -1, LocalDateTime.now(), "....Ma mnogo HOT, basi....", 0);
//	CommentDAO.getInstance().addComment(comment);
//	
//	Set<Gag> gags2 = GagDAO.getInstance().getAllGags();
//	System.out.println(gags2);
//	
//	System.out.println("======================================================\n");
//	System.out.println("====================  	HOT		===============\n");
//	
//	
//	List hot = GagDAO.getInstance().hotGags();
//	System.out.println(hot);
//	
//System.out.println("======================	TRENDING 	=======================\n");
//	
//	List TREN = GagDAO.getInstance().trendingGags();
//	System.out.println(TREN);
//	
//	
//System.out.println("======================	FRESH 	=======================\n");
//	
//	List fresh = GagDAO.getInstance().freshGags();
//	System.out.println(fresh);
//	
//	System.out.println("======================	ALL 	=======================\n");
//	
	Map gags3 = GagDAO.getInstance().getAllGags();
//	System.out.println(gags3);
	
//System.out.println("======================	savage 	=======================\n");
//	
//	List gags4 =  GagDAO.getInstance().categoryGags("savage");
//	System.out.println(gags4);
//System.out.println("======================	FUNNY 	=======================\n");
//	
//	 gags4 = GagDAO.getInstance().categoryGags("funny");
//	System.out.println(gags4);
//	
	
	Map<String, User> useri = UserDAO.getInstance().getAllUsers();
	
//	System.out.println("=====================		USERS		======================\n");
////	
//	System.out.println(useri);
//		Comment c = null;
//		for(Comment com : CommentDAO.allComments) {
//			if(com.getCommentId() == 3)
//				c = com;
//		}
//		System.out.println(c);
//		CommentDAO.getInstance().deleteComment(c);
		
//		GagDAO.getInstance().getAllGags();
//		Gag gag = null;
//		for(Gag g : GagDAO.getInstance().allGags) {
//			if(g.getGagID() == 2)
//				gag = g;
//		}
//		GagDAO.getInstance().deleteGag(gag);
	User u = UserDAO.getInstance().getAllUsers().get(0);
	
	//System.out.println(u);
	

	System.out.println("============================Commented==========================\n");

	System.out.println(GagDAO.getInstance().allCommentedGags(1).toString());
	}

}
