package com.example.controller;

import java.sql.SQLException;
import java.util.Base64;

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

import com.example.model.User;
import com.example.model.dao.UserDAO;

import com.example.model.dao.RegisterDAO;

@Controller
public class UserController {
	

//	@RequestMapping(value="/index", method=RequestMethod.GET)
//	public String sayHi(Model viewModel, HttpServletRequest request) {
//		
//		return "index";  
//	}
	
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String profile(Model viewModel, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("logged")== null || (boolean) session.getAttribute("logged")==false){
			return "redirect:/index";
		}
		return  "profile";
	}
	
	
	@RequestMapping(value="/profile/{userId}", method=RequestMethod.GET)
	public String profiles(@PathVariable("userId") String id, Model model, HttpServletRequest request) throws SQLException {
		if(!id.matches("[0-9]+")){
			
			return "redirect:/Jape/index";
		}
		Long userId = Long.valueOf(id);
		System.out.println(UserDAO.getInstance().getAllUsers().get(UserDAO.getInstance().getUser(UserDAO.getInstance().getUserEmail(5))));
		try {
				model.addAttribute("profil",  UserDAO.getInstance().getUserById(userId) );
				
				return  "userProfile";
			
		} catch (SQLException e) {
			System.out.println("profile/userId: " + e.getMessage());
		}
		
		return  "errorPage";
	}
	
	@RequestMapping(value="/settings", method=RequestMethod.GET)
	public String settings(Model viewModel, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("logged")== null || (boolean) session.getAttribute("logged")==false){
			return "redirect:/index";
		}
		return  "profileSettings";
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String loginGeft(Model viewModel, HttpServletRequest request) {
		// talk with model viewModel.addAttribute("Text","Hello");
		
		return  "testFile";
	}
	
	

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginGet(Model viewModel, HttpServletRequest request) {
		// talk with model viewModel.addAttribute("Text","Hello");
		HttpSession session = request.getSession();
		if(session.getAttribute("logged")!= null && (boolean) session.getAttribute("logged")==true){
			return "redirect:/index";
		}
		return  "login";
	}
	
	@RequestMapping (value="/login", method=RequestMethod.POST)
	public String login(Model viewModel, HttpServletRequest request) {
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		
		HttpSession session = request.getSession();
		
		try {
			//
			if(!UserDAO.getInstance().getAllUsers().containsKey(email)) {
				System.out.println(UserDAO.getInstance().getAllUsers().containsKey(email));
				session.setAttribute("notAMember", "Wrong username or password!");
				System.out.println("wrong email + " + email);
				return "login";
			}
		} catch (SQLException e) {
			//error page
			System.out.println("Could not getAllUsers in LoginServlet: " + e.getMessage());
		}
		
		try {
			String url = "";
			if(UserDAO.getInstance().validateLogin(email, password)) {
				if(!UserDAO.getInstance().isVerified(email)){
					session.setAttribute("notAMember", "Account not verified!");
					System.out.println("Account not verified");
					return "login";
				}
				 
				User user = UserDAO.getInstance().getUser(email);
				session.setAttribute("logged", true);
				session.setAttribute("user", user );
				session.setAttribute("userGags", user.myGags());
				session.setAttribute("repliedTo", 0);
				session.setAttribute("userId", UserDAO.getInstance().getAllUsers().get(email).getUserId());

				System.out.println("kk, " + email + " has logged in");
				//when profile is done, return index with everything
				//url = "index";
				System.out.println(((User)session.getAttribute("user")).getProfilePic());
				url = "redirect:/profile";
			}
			else {
				session.setAttribute("notAMember", "Wrong username or password!");
				
				System.out.println("wrong password");
				url = "login";
			}
			return url;
		} catch (SQLException e) {
			System.out.println("SQL Exception in login UserController  +" + e.getMessage());
			return "errorPage";
		}
		
	}
	
//	 @RequestMapping (value="/popUp", method=RequestMethod.GET)
//	 public String popUp(Model viewModel, HttpServletRequest request) {
//	        return "popUp";
//	 }
	
	 
	 
	
	 @RequestMapping (value="/logout", method=RequestMethod.GET)
	 public String logout(Model viewModel, HttpServletRequest request) {
		 
		 HttpSession session = request.getSession();
			if(session.getAttribute("logged")!= null && (boolean) session.getAttribute("logged")==false){
				return "redirect:/index";
			}
			//<<display in console result..
		 User user = (User) session.getAttribute("user");
		 System.out.println( ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		// System.out.println("User  " + user.getUsername() + " logged out");
		 System.out.println( ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		 //>>
		 session.removeAttribute("repliedTo");
		 	session.setAttribute("logged", false);
	        session.invalidate();
	        return "redirect:/index";
	 }
	 
	 @RequestMapping(value="/register", method=RequestMethod.GET)
		public String goToRegister(Model viewModel, HttpServletRequest request) {
			HttpSession session = request.getSession();
			if(session.getAttribute("logged")!= null && (boolean) session.getAttribute("logged")==true){
				return "redirect:/index";
			}
			// talk with model viewModel.addAttribute("Text","Hello");
			return  "register";
		}
	 
	 @RequestMapping (value="/register", method=RequestMethod.POST)
	 public String register(Model viewModel, HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		String username = req.getParameter("username");
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
	
		String passConfirm = req.getParameter("passConfirm");
		
		//if passwords in password and confirm password
		//did not match
		if(!password.equals(passConfirm)){
			session.setAttribute("registerResult", "pass not match");
			return "register";
		}
		//if user is inserted into db
		
		try {
			if(RegisterDAO.getInstance().register(username, email, password)){	
				//should send email to verify account
				session.setAttribute("registerResult", "Check your email. You should verify your acount!");
				return "register";
			}
			else{
				//error page
			}
		} catch (SQLException e) {
			//error page
		}
		return "";
	 }
	 
	 
	 
	 @RequestMapping (value="/verification", method=RequestMethod.GET)
	 @ResponseBody
	 public String verify(@RequestParam String email,@RequestParam String verificationKey, Model viewModel, HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		
		
		System.out.println(email); 
		System.out.println(verificationKey);
		
		
		
		//String email = req.getParameter("email");
		//String key = req.getParameter("verificationKey");
		System.out.println("Vyv verification sme ");
		System.out.println(email);
		System.out.println(verificationKey);
		try {
			
			if(!UserDAO.getInstance().exists(email)){
				System.out.println("ne syshtestvuva toq email");
				session.setAttribute("verificationResult", "acc not exist");
				return "notverified";
			}
			else { 
				 
				if(!UserDAO.getInstance().isVerified(email)){
					boolean verifying = UserDAO.getInstance().verify(email,verificationKey);
					if(verifying) {
						System.out.println("Account verified!");
					
						session.setAttribute("verificationResult", "Account verified!");
						return "verified";
					}
					else { 
						System.out.println(verifying);
						System.out.println("ne yspq da go verificira");
						session.setAttribute("verificationResult", "Sorry, could not verify :(");
						return "notverified";
					}
				}	
				else {
					session.setAttribute("verificationResult", "Account already verified...");
					System.out.println("already verified");
					return "alreadyverified";
					}
			}
			
		} catch (SQLException e) {
			 //error page	 
		}
		return "notverified";
	 }
	
	 //;lkslsdfkl;sdfk
//	 @RequestMapping (value="/accountSettings", method=RequestMethod.POST)
		public String accountSettingsChaned( HttpServletRequest request) {
		 String oldPassword = request.getParameter("oldPassword");
		 String password = request.getParameter("password");
		 String passConfirm = request.getParameter("passConfirm");

		 
			
		return "gagPage";
		}
	 
	 @RequestMapping (value="/password", method=RequestMethod.GET)
		public String passwordChangeGet( HttpServletRequest request) {
		 HttpSession session = request.getSession();
		 if(session.getAttribute("logged")== null || (boolean) session.getAttribute("logged")==false){
				return "redirect:/index";
			}
		 return "passwordSettings";
		}
	 
	 
	 @RequestMapping (value="/password", method=RequestMethod.POST)
		public String passwordChange( HttpServletRequest request) {
		 HttpSession session = request.getSession();
		 String oldPassword = request.getParameter("oldPassword");
		 String password = request.getParameter("password");
		 String passConfirm = request.getParameter("passConfirm");
		 User user =(User) session.getAttribute("user");
		 
		 if(user.getPassword().equals(oldPassword.trim())){
			 if(password.equals(passConfirm)){
				 try {
					UserDAO.getInstance().changePass(password, user);
					user.setPassword(password);
					session.setAttribute("passwordChangeMessage", "Password changed!");
				} catch (SQLException e) {
					System.out.println("could not change passWord "+ e.getMessage());
					session.setAttribute("passwordChangeMessage", "Sorry, something went wrong, could not update password...");
					return "passwordSettings";
				}
			 }
			 else{
				 session.setAttribute("passwordChangeMessage", "New passwords did not match.");
			 }
		 }
		 else{
			 session.setAttribute("passwordChangeMessage", "Wrong password!");
		 }
		return "passwordSettings";
		}
	 
	 
	 
	 @RequestMapping (value="/account", method=RequestMethod.GET)
		public String accountChangeGet( HttpServletRequest request) {
		 HttpSession session = request.getSession();
		 if(session.getAttribute("logged")== null || (boolean) session.getAttribute("logged")==false){
				return "redirect:/index";
			}
		 return "accountSettings";
		}
	 
	 
	 @RequestMapping (value="/account", method=RequestMethod.POST)
		public String accountChange(HttpServletRequest request) {
		 HttpSession session = request.getSession();
		 String username = request.getParameter("username");
		 //TODO set isValidated to false; send validation email; ask for validation on log in
		// String email = request.getParameter("email");
		 String isNsfw = request.getParameter("nsfw");
		 String password = request.getParameter("password");
		 User user =(User) session.getAttribute("user");
		 boolean nsfw = user.isViewNsfwContent();
		 
		 if(user.getPassword().equals(password.trim())){
			 if(isNsfw.equals("true") || isNsfw.equals("false")){
				  nsfw = Boolean.valueOf(isNsfw);
			 }
			 else{
				 session.setAttribute("accountChangedMessage", "Oopss.. something went wrong, account not updated.");
			 }
				 try {
					UserDAO.getInstance().changeNSFW(nsfw, user);
					user.setViewNsfwContent(nsfw);
					session.setAttribute("accountChangedMessage", "Changed nsfw... could not change username, sorry.");
					UserDAO.getInstance().changeUsername(username, user);
					user.setUsername(username);
					session.setAttribute("accountChangedMessage", "Changes saved!");
				} catch (SQLException e) {
					System.out.println("could not change passWord "+ e.getMessage());
					session.setAttribute("accountChangedMessage", "Sorry, something went wrong, could not update account...");
					return "accountSettings";
				}
			 }

		 else{
			 session.setAttribute("accountChangedMessage", "Wrong password!");
		 }
		return "accountSettings";
		}

	 
	 @RequestMapping (value="/gagPage/{gagId}", method=RequestMethod.GET)
		public String gagPage(@PathVariable("gagId") String id, HttpServletRequest request) {
		 
		return "gagPage";
		}
	 
	 @RequestMapping (value="/upvote", method=RequestMethod.GET)
	 public String upvote(HttpServletRequest request, HttpSession session) {
		 //check if gag id is not in likedGags
		String gagIdS = (request.getParameter("gagId"));
		Long gagId = Long.parseLong(gagIdS);
		//if it's not there or it's there with -1 points - insert with 1 point
		 User u = (User)session.getAttribute("user");
		 System.out.println(u);
		 System.out.println(gagId);
		 
		if(!u.getLikedGags().containsKey(gagId) ) {
			UserDAO.getInstance().vote(u.getUserId(), gagId, 1);
			
			System.out.println("voted in controller");
			
		} else if(u.getLikedGags().get(gagId) == -1) {
			UserDAO.getInstance().switchVote(u.getUserId(), gagId, 1);
			System.out.println("switch-voted");
			
		} else {
			UserDAO.getInstance().unvote(u.getUserId(), gagId, 1);
			System.out.println("removed vote in controller");
		}

		//return previous page 
		return "index";
	 }
	 
	 @RequestMapping (value="/downvote", method=RequestMethod.GET)
	 public String downvote(HttpServletRequest request, HttpSession session) {
		 //check if gag id is not in likedGags
			String gagIdS = (request.getParameter("gagId"));
			Long gagId = Long.parseLong(gagIdS);
			//if it's not there or it's there with -1 points - insert with 1 point
			 User u = (User)session.getAttribute("user");
			 System.out.println(u);
			 System.out.println(gagId);
			 
			 if(!u.getLikedGags().containsKey(gagId) ) {
					UserDAO.getInstance().vote(u.getUserId(), gagId, -1);
					
					System.out.println("voted in controller");
					
				} else if(u.getLikedGags().get(gagId) == 1) {
					UserDAO.getInstance().switchVote(u.getUserId(), gagId, -1);
					System.out.println("switch-voted");
					
				} else {
					UserDAO.getInstance().unvote(u.getUserId(), gagId, -1);
					System.out.println("removed vote in controller");
				}

				//return previous page 
				return "index";
			 }
}
