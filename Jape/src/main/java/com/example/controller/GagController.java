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
			
		case "cosplay":
			session.setAttribute("gags", gd.categoryGags("cosplay"));
			break;
		case "funny":
			session.setAttribute("gags", gd.categoryGags("cosplay"));
			break;
		case "gif":
			session.setAttribute("gags", gd.categoryGags("gif"));
			break;
		case "savage":
			session.setAttribute("gags", gd.categoryGags("savage"));
			break;
		case "sport":
			session.setAttribute("gags", gd.categoryGags("sport"));
			break;
		case "wtf":
			session.setAttribute("gags", gd.categoryGags("wtf"));
			break;
		case "yummy":
			session.setAttribute("gags", gd.categoryGags("yummy"));
			break;
		
		}
	}
	
	@RequestMapping (value="/index", method=RequestMethod.GET)
	public String getHotGags(Model viewModel, HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		UserDAO.getInstance().getAllUsers();
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		
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

	@RequestMapping (value="/cosplay", method=RequestMethod.GET)
	public String getCosplay(HttpServletRequest request) {
		
		gagLoader("cosplay", request);
		
		return "index";
	}
	
	@RequestMapping (value="/funny", method=RequestMethod.GET)
	public String getFunny(HttpServletRequest request) {
		
		gagLoader("funny", request);
		
		return "index";
	}
	
	@RequestMapping (value="/gif", method=RequestMethod.GET)
	public String getGif(HttpServletRequest request) {
		
		gagLoader("gif", request);
		
		return "index";
	}
	
	@RequestMapping (value="/savage", method=RequestMethod.GET)
	public String getSavage(HttpServletRequest request) {
		
		gagLoader("savage", request);
		
		return "index";
	}
	
	@RequestMapping (value="/sport", method=RequestMethod.GET)
	public String getSport(HttpServletRequest request) {
		
		gagLoader("sport", request);
		
		return "index";
	}
	
	@RequestMapping (value="/wtf", method=RequestMethod.GET)
	public String getWTF(HttpServletRequest request) {
		
		gagLoader("wtf", request);
		
		return "index";
	}
	
	@RequestMapping (value="/yummy", method=RequestMethod.GET)
	public String getYummy(HttpServletRequest request) {
		
		gagLoader("yummy", request);
		
		return "index";
	}
	
	
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



	
	
	


