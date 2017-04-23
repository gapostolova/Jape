package com.example.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.UserManager;
import com.example.model.dao.UserDAO;

import com.example.model.dao.RegisterDAO;

@Controller
public class UserController {
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String sayHi(Model viewModel) {
		// talk with model
		
		return "index";  
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String goToRegister(Model viewModel) {
		// talk with model viewModel.addAttribute("Text","Hello");
		return  "register";
	}

	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model viewModel) {
		// talk with model viewModel.addAttribute("Text","Hello");
		return  "redirect:login";
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
				System.out.println("session : " + session.getAttribute("notAMember"));
				System.out.println("wrong email + " + email);
				return "login";
			}
		} catch (SQLException e) {
			//error page
			System.out.println("Could not getAllUsers in LoginServlet: " + e.getMessage());
		}
		
		try {
			String url = "";
			if(UserManager.getInstance().validateLogin(email, password)) {
				if(!UserDAO.getInstance().isVerified(email)){
					session.setAttribute("notAMember", "Account not verified!");
					System.out.println("session : " + session.getAttribute("notAMember"));
					System.out.println("Account not verified");
					return "login";
				}
				session.setAttribute("logged", true);
				session.setAttribute("user", UserDAO.getInstance().getUser(email));
				System.out.println("kk, " + email + " has logged in");
				url = "index";
			}
			else {
				System.out.println("wrong password");
				session.setAttribute("notAMember", "Wrong username or password!");
				System.out.println(session.getAttribute("notAMember") + " + session");
				
				url = "login";
			}
			return url;
		} catch (SQLException e) {
			System.out.println("SQL Exception in login UserController  +" + e.getMessage());
			return "errorPage";
		}
		
	}
	
	 @RequestMapping (value="/popUp", method=RequestMethod.GET)
	 public String popUp(Model viewModel, HttpServletRequest request) {
	        return "popUp";
	 }
	
	
	 @RequestMapping (value="/logout", method=RequestMethod.POST)
	 public String logout(Model viewModel, HttpServletRequest request) {
		 HttpSession session = request.getSession();
			session.setAttribute("logged", false);
	        session.invalidate();
	        return "index";
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
				session.setAttribute("registerResult", "verify");
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
				session.setAttribute("verificationResult", "acc not exist");
				return "notverified";
			}
			else { 
				 
				if(!UserDAO.getInstance().isVerified(email)){
					if(UserDAO.getInstance().verify(email,verificationKey)) {
						System.out.println("Account verified!");
					
						session.setAttribute("verificationResult", "Account verified!");
						return "verified";
					}
					else { 
						session.setAttribute("verificationResult", "Sorry, could not verify :(");
						return "notverified";
					}
				}	
				else {
					session.setAttribute("verificationResult", "Account already verified...");
					return "alreadyverified";
					}
			}
			
		} catch (SQLException e) {
			 //error page	 
		}
		return "notverified";
	 }
	
}
