package com.example.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.UserManager;
import com.example.model.dao.UserDAO;

import com.example.model.dao.RegisterDAO;

@Controller
public class UserController {
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String sayHi(Model viewModel) {
		// talk with model
		
		viewModel.addAttribute("Text","Hello");
		
		return "register";  
	}
	
	@RequestMapping (value="/login", method=RequestMethod.POST)
	public String login(Model viewModel, HttpServletRequest request) {
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		HttpSession session = request.getSession();
		
		try {
			if(UserDAO.getInstance().getAllUsers().containsKey(email)) {
				session.setAttribute("notAMember", "It seems like you don't have an account yet! ");
				return "register";
			}
		} catch (SQLException e) {
			//error page
			System.out.println("Could not getAllUsers in LoginServlet: " + e.getMessage());
		}
		
		try {
			String url = "";
			if(UserManager.getInstance().validateLogin(email, password)) {
				session.setAttribute("logged", true);
				session.setAttribute("user", UserDAO.getInstance().getUser(email));
				url = "index";
			}
			else {
				url = "login";
			}
			return url;
		} catch (SQLException e) {
			//error page
		}
		return "";
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
		
		
		if(!password.equals(passConfirm)){
			session.setAttribute("registerResult", "pass not match");
			return "register";
		}
		
		if(RegisterDAO.getInstance().register(username, email, password)){	
			session.setAttribute("registerResult", "verify");
			return "register";
		}
		else{
			//error page
		}
		return "";
	 }
	 
	 @RequestMapping (value="/verification", method=RequestMethod.GET)
	 public String verify(Model viewModel, HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		String email = req.getParameter("email");
		String key = req.getParameter("verificationKey");
		try {
			
			if(!UserDAO.getInstance().exists(email)){
				session.setAttribute("verificationResult", "acc not exist");
			}
			else { 
				if(!UserDAO.getInstance().isVerified(email)){
					if(UserDAO.getInstance().verify(email,key)) {
						session.setAttribute("verificationResult", "ok");
					}
					else { 
						session.setAttribute("verificationResult", "again");
					}
				}	
				else {
					session.setAttribute("verificationResult", "already ok");
					}
			}
			return "verification";
		} catch (SQLException e) {
			 //error page	 
		}
		return "";
	 }
	
}
