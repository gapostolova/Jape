package com.example.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

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

	private void gagLoader(String category,  HttpServletRequest request) {
		HttpSession session = request.getSession();
		GagDAO gd = GagDAO.getInstance();
		switch (category) {
		case "hot":			
			
			try {
				UserDAO.getInstance().getAllUsers();
				gd.getAllGags();
			} catch (SQLException e) {
				// TODO error page
				System.out.println(e);
			}
			session.setAttribute("gags", gd.hotGags());
			break;

		case "trending":
			session.setAttribute("gags", gd.trendingGags());
			break;
			
		case "fresh":
			session.setAttribute("gags", gd.freshGags());
			break;
		}
	}
	
	@RequestMapping (value="/index", method=RequestMethod.GET)
	public String getHotGags(Model viewModel, HttpServletRequest request) throws SQLException {
		
		gagLoader("hot", request);
		
		return "index";
	}
	
	@RequestMapping (value="/trending", method=RequestMethod.GET)
	public String getTrendingGags(HttpServletRequest request) {
		
		gagLoader("trending", request);
		
		return "index";
	}
	
	@RequestMapping (value="/fresh", method=RequestMethod.GET)
	public String getFreshGags(HttpServletRequest request) {
		
		gagLoader("fresh", request);
		
		return "index";
	}
	
	@RequestMapping (value="/view/{gagId}", method=RequestMethod.GET)
	public String viewGag(@PathVariable("gagId") String gagId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		try {
			session.setAttribute("gag", GagDAO.getInstance().getAllGags().get(Long.parseLong(gagId)));
		} catch (SQLException e) {
			//error page
			System.out.println(e);
		}
		
		return "viewGag";
	}
	
	@RequestMapping (value="/search", method=RequestMethod.GET)
	public String searchGags(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		String keyword = request.getParameter("keyword").toLowerCase().trim();
		String[] keywords = keyword.split(" ");
		
		ArrayList<Gag> searchResult = new ArrayList<>();
		
		try {
			for(Gag gag : GagDAO.getInstance().getAllGags().values()) {
				for (int i = 0; i < keywords.length; i++) {
					if(gag.getTitleLower().contains(keywords[i]))
						searchResult.add(gag);
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
			//error page
		}
		
		session.setAttribute("gags", searchResult.size() > 0?searchResult:null);
		
		return "index";
	}
	
	
	
}