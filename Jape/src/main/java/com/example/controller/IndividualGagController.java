package com.example.controller;

import static org.mockito.Matchers.longThat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Comment;
import com.example.model.Gag;
import com.example.model.User;
import com.example.model.UserManager;
import com.example.model.dao.UserDAO;
import com.example.model.dao.CommentDAO;
import com.example.model.dao.GagDAO;
import com.example.model.dao.RegisterDAO;

@Controller
public class IndividualGagController {
	

private static final int DEFAULT_COMMENT_ID = -1;

//	@RequestMapping(value="/index", method=RequestMethod.GET)
//	public String sayHi(Model viewModel, HttpServletRequest request) {
//		
//		return "index";  
//	}
	
	
	
	@RequestMapping(value="/jape/{gagId}", method=RequestMethod.GET)
	public String japeGet(@PathVariable ("gagId") String id, Model model, HttpServletRequest request) {
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
				model.addAttribute("currentOpenGag", allGags.get(gagId));
			//	session.setAttribute("gag", allGags.get(gagId));
				
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
	
	@RequestMapping(value="/comment/{gagId}", method=RequestMethod.POST)
	public String postComment(@PathVariable ("gagId") String id, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Gag gag=null;
		
		if( session.getAttribute("logged")==null||(boolean) session.getAttribute("logged")==false || session.getAttribute("user")==null || session.getAttribute("repliedTo")==null){
			session.setAttribute("notAMember", "Log in to comment!");
			return "redirect:/login";
		}
		
		//if someone messed with the html
		if(request.getParameter("message")==null || request.getParameter("message").trim().isEmpty()){
			return "redirect:/index";
		}
	
		
		String content = request.getParameter("message");
		User user =(User) session.getAttribute("user");
		
		//if not correct url
		if(!id.matches("[0-9]+")){
			return "errorPage";
		}
		long gagId = Long.valueOf(id);
		
		Map<Long, Gag> allGags;
		try {
			allGags =  GagDAO.getInstance().getAllGags();
			if(allGags.containsKey(gagId)){
				System.out.println(" GAG ID in GAG PAGE: " +allGags.get(gagId) );
				gag =  allGags.get(gagId);
				model.addAttribute("currentOpenGag",gag);
				
				
			//	session.setAttribute("gag", allGags.get(gagId));	
			}
			else{
				return "index";
			}
			
			
		} catch (SQLException e) {
			System.out.println("Could not visualize individual gag: " + e.getMessage());
		}
		
		//TODO
		//tuk mislq, che trqbva da se pazi v modela
		//sega shte go napravq v sesiqta, za da raboti
		//tova shte raboti samo ako nqma drugi otvoreni stranici...
		//zashtoto togava gag shte se promeni, a ako sme natusnali na buton za otgovor, togava shte pishe comm na drugiq post
		
		//TODO put repliedTo in model
		
		
		if(content.trim().isEmpty()){
			//set attribute to say that the comment was only spaces/empty
			System.out.println("*** Empty content of comment... ****");
			return "gag";
		}
		
		if(session.getAttribute("repliedTo")==null){
			session.setAttribute("repliedTo", 0l);
		}
		
		//when comment a comment starts working
		//long mothershipId = (long) session.getAttribute("repliedTo");
		session.setAttribute("repliedTo", 0l);
		long mothershipId = 0l;
	//	System.out.println("**********"+mothershipId+"**********");
		//pri log in setSessionAttr("repliedTo",0)
		//pri log out removeSessionAttr("repliedTo)
		
		if(mothershipId== 0 || CommentDAO.getInstance().commentidExists(mothershipId)){
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
		return  ("redirect:/jape/"+gagId);
	}
	
	 
}
