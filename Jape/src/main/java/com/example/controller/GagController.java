package com.example.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
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

import com.example.model.Category;
import com.example.model.Gag;
import com.example.model.User;
import com.example.model.dao.GagDAO;
import com.example.model.dao.UserDAO;

import io.undertow.server.session.Session;

@Controller
public class GagController {
		
	static enum Categories {HOT, TRENDING, FRESH, COSPLAY, FUNNY, GIF, SAVAGE, SPORT, WTF, YUMMY};

	private void gagLoader(Categories category,  HttpServletRequest request) {
		HttpSession session = request.getSession();
		GagDAO gd = GagDAO.getInstance();
		
		switch(category) {
		case HOT:			
			
			try {
				UserDAO.getInstance().getAllUsers();
				gd.getAllGags();
			} catch (SQLException e) {
				// TODO error page
				System.out.println(e);
			}
			session.setAttribute("gags", gd.hotGags());
			break;

		case TRENDING:
			session.setAttribute("gags", gd.trendingGags());
			break;
			
		case FRESH:
			session.setAttribute("gags", gd.freshGags());
			break;
			
		case COSPLAY:
			session.setAttribute("gags", gd.categoryGags("cosplay"));
			break;
		case FUNNY:
			session.setAttribute("gags", gd.categoryGags("funny"));
			break;
		case GIF:
			session.setAttribute("gags", gd.categoryGags("gif"));
			break;
		case SAVAGE:
			session.setAttribute("gags", gd.categoryGags("savage"));
			break;
		case SPORT:
			session.setAttribute("gags", gd.categoryGags("sport"));
			break;
		case WTF:
			session.setAttribute("gags", gd.categoryGags("wtf"));
			break;
		case YUMMY:
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
		
		gagLoader(Categories.HOT, request);
		
		return "index";
	}
	
	@RequestMapping (value="/trending", method=RequestMethod.GET)
	public String getTrendingGags(HttpServletRequest request) {
		
		gagLoader(Categories.TRENDING, request);
		
		return "index";
	}
	
	@RequestMapping (value="/fresh", method=RequestMethod.GET)
	public String getFreshGags(HttpServletRequest request) {
		
		gagLoader(Categories.FRESH, request);
		
		return "index";
	}

	@RequestMapping (value="/cosplay", method=RequestMethod.GET)
	public String getCosplay(HttpServletRequest request) {
		
		gagLoader(Categories.COSPLAY, request);
		
		return "index";
	}
	
	@RequestMapping (value="/funny", method=RequestMethod.GET)
	public String getFunny(HttpServletRequest request) {
		
		gagLoader(Categories.FUNNY, request);
		
		return "index";
	}
	
	@RequestMapping (value="/gif", method=RequestMethod.GET)
	public String getGif(HttpServletRequest request) {
		
		gagLoader(Categories.GIF, request);
		
		return "index";
	}
	
	@RequestMapping (value="/savage", method=RequestMethod.GET)
	public String getSavage(HttpServletRequest request) {
		
		gagLoader(Categories.SAVAGE, request);
		
		return "index";
	}
	
	@RequestMapping (value="/sport", method=RequestMethod.GET)
	public String getSport(HttpServletRequest request) {
		
		gagLoader(Categories.SPORT, request);
		
		return "index";
	}
	
	@RequestMapping (value="/wtf", method=RequestMethod.GET)
	public String getWTF(HttpServletRequest request) {
		
		gagLoader(Categories.WTF, request);
		
		return "index";
	}
	
	@RequestMapping (value="/yummy", method=RequestMethod.GET)
	public String getYummy(HttpServletRequest request) {
		
		gagLoader(Categories.YUMMY, request);
		
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
		
		HashSet<Gag> tempGags = new HashSet<>();
		ArrayList<Gag> searchResult = new ArrayList<>();
		
		try {
			for(Gag gag : GagDAO.getInstance().getAllGags().values()) {
				for (int i = 0; i < keywords.length; i++) {
					if(gag.getTitleLower().contains(keywords[i]))
						tempGags.add(gag);
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
			//error page
		}
		
		searchResult.addAll(tempGags);
		session.setAttribute("gags", searchResult.size() > 0?searchResult:null);
		
		return "index";
	}
	
	@RequestMapping (value="/video", method=RequestMethod.GET)
	public String video(HttpServletRequest request) {
		
		return "video";
	}
	@RequestMapping (value="/uploadVideo", method=RequestMethod.GET)
	public String uploadVideo(HttpServletRequest request) {
		
		return "uploadVideo";
	}
	
	
	@RequestMapping (value="/uploadVideo", method=RequestMethod.POST)
	public String uploadYouTubeVideo(@RequestParam("title") String title,
			@RequestParam("url") String url, HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		
		if(title.trim().isEmpty() || url.trim().isEmpty() ){
			model.addAttribute("problem", "Title/url can't be empty!");
			return "uploadVideo";
		}
		
		if(url.contains("=")){
			String[] link = url.split("=");
			if(link.length != 2 ){
				model.addAttribute("problem", "Incorrect url");
				//make a new gag, add category Video
				//https://www.youtube.com/embed/JntTS-7uMXg
				
				String embedLink = "https://www.youtube.com/embed/"	+ link[1];
				Gag gag = new Gag(embedLink, title, ((User) session.getAttribute("user")).getUserId(), false, true);
				gag.addCategory(new Category(9, "YOUTUBE"));
				//add to data base,
				
				
			}
		}
		
		
		
		return "video";
	}
	
	
	
	
	

}



	
	
	


