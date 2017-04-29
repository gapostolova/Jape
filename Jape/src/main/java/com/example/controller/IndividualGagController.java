package com.example.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Comment;
import com.example.model.Gag;
import com.example.model.User;
import com.example.model.dao.CommentDAO;
import com.example.model.dao.GagDAO;

@Controller
public class IndividualGagController {
	

private static final int DEFAULT_COMMENT_ID = -1;

//	@RequestMapping(value="/index", method=RequestMethod.GET)
//	public String sayHi(Model viewModel, HttpServletRequest request) {
//		
//		return "index";  
//	}
	
	
	
	@RequestMapping(value="/jape/{gagId}", method=RequestMethod.GET)
	public String japeGet(@PathVariable ("gagId") String id, Model viewModel, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(!id.matches("[0-9]+")){
			return "redirect:/Jape/index";
		}
		Long gagId = Long.valueOf(id);
		System.out.println(" GAG ID in GAG PAGE: " + gagId);
		Map<Long, Gag> allGags;
		try {
			allGags =  GagDAO.getInstance().getAllGags();
			if(allGags.containsKey(gagId)){
				System.out.println(" GAG ID in GAG PAGE: " +allGags.get(gagId) );
				session.setAttribute("gag", allGags.get(gagId));
				//if here return viewGag
				return "gag";
			}
			
		} catch (SQLException e) {
			System.out.println("Could not visualize individual gag: " + e.getMessage());
		}
	
		return  "index";
	}
	
	
	@RequestMapping(value="/jape", method=RequestMethod.POST)
	public String jape(Model viewModel, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		return  "gag";
	}
	
	@RequestMapping(value="/comment", method=RequestMethod.GET)
	public String getComment(Model viewModel, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("repliedTo")==null){
			return "errorPage";
		}
		
		System.out.println("**********"+session.getAttribute("repliedTo")+"**********");
		return  "gag";
	}
	
	@RequestMapping(value="/comment", method=RequestMethod.POST)
	public String postComment(Model viewModel, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String content = request.getParameter("message");
		User user =(User) session.getAttribute("user");
		Gag gag = (Gag) session.getAttribute("gag");
		//TODO
		//tuk mislq, che trqbva da se pazi v modela
		//sega shte go napravq v sesiqta, za da raboti
		//tova shte raboti samo ako nqma drugi otvoreni stranici...
		//zashtoto togava gag shte se promeni, a ako sme natusnali na buton za otgovor, togava shte pishe comm na drugiq post
		
		if(session.getAttribute("repliedTo")==null || session.getAttribute("logged")==null ||(boolean) session.getAttribute("logged")==false){
			session.setAttribute("notAMember", "Log in to comment!");
			return "redirect:/login";
		}
		
		if(content.trim().isEmpty()){
			//set attribute to say that the comment was only spaces/empty
			return "gag";
		}
		long mothershipId= (long) session.getAttribute("repliedTo");
		session.setAttribute("repliedTo", 0);
		System.out.println("**********"+mothershipId+"**********");
		//pri log in setSessionAttr("repliedTo",0)
		//pri log out removeSessionAttr("repliedTo)
		
		if(CommentDAO.getInstance().commentidExists(mothershipId)){
			Comment com = new Comment(user.getUserId(), gag.getGagID(), DEFAULT_COMMENT_ID, LocalDateTime.now(), content, mothershipId);
			try {
				CommentDAO.getInstance().addComment(com);
				
				
			} catch (SQLException e) {
				System.out.println("Could not add comment: "+ e.getMessage());
				return "errorPage";
			}
		}
		else{
			return "errorPage";
		}
		
		return  "gag";
	}
	
	 
}
