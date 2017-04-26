package com.example.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Gag;
import com.example.model.dao.GagDAO;
import com.example.model.dao.UserDAO;

@Controller
public class GagController {
		
//	private Gag getGag(int page, int position) throws SQLException {
//		UserDAO.getInstance().getAllUsers();
//		GagDAO.getInstance().getAllGags();
//		
//		Gag gag = GagDAO.getInstance().hotGags().get(page * 3 + position);
//		
//		return gag;
//	}

	//throws for testing reasons!!!!!
	//remove!!!

	@RequestMapping (value="/index", method=RequestMethod.GET)
	public String getGags(Model viewModel, HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession();
		
		UserDAO.getInstance().getAllUsers();
		GagDAO.getInstance().getAllGags();
		session.setAttribute("gags", GagDAO.getInstance().hotGags());
		return "index";
	}
	
//	@RequestMapping (value="/view/{gagId}", method=RequestMethod.GET)
//	public void viewGag(@PathVariable("gagId") String gagId, HttpServletRequest request) throws SQLException {
//
//		HttpSession session = request.getSession();
//		
//		
//		
//		int page = Integer.parseInt(request.getParameter("page")!=null?request.getParameter("page"):"0");
//		for (int i = 0; i < 3; i++) {
//			session.setAttribute("gag"+i, getGag(page, i));
//		}

//	}
	
	@RequestMapping (value="/view/{gagId}", method=RequestMethod.GET)
	public String viewGag(@PathVariable("gagId") String id, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Long gagId = Long.valueOf(id);
		System.out.println(" GAG ID in GAG PAGE: " + gagId);
		Map<Long, Gag> allGags;
		try {
			allGags =  GagDAO.getInstance().getAllGags();
			if(allGags.containsKey(gagId)){
				System.out.println(" GAG ID in GAG PAGE: " +allGags.get(gagId) );
				
				session.setAttribute("gag", allGags.get(gagId));
				//if here return viewGag
			}
			
		} catch (SQLException e) {
			
		}
		//when here return error page
		return "viewGag";
	}
	
	
	@RequestMapping (value="/getgags", method=RequestMethod.GET)
	public String getgags() {
		return "getGags";
	}
}


