package com.example.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Gag;
import com.example.model.dao.GagDAO;
import com.example.model.dao.UserDAO;

@Controller
public class GagController {
		
	private Gag getGag(int page, int position) throws SQLException {
		UserDAO.getInstance().getAllUsers();
		
		return GagDAO.getInstance().hotGags().get(page * 3 + position);
	}

	//throws for testing reasons!!!!!
	//remove!!!
	@RequestMapping (value="/index", method=RequestMethod.GET)
	public String getGags(Model model, HttpServletRequest request) throws SQLException {
		HttpSession session = request.getSession();
		int page = Integer.parseInt(request.getParameter("page")!=null?request.getParameter("page"):"0");
		for (int i = 0; i < 3; i++) {
			session.setAttribute("gag"+i, getGag(page, i));
		}
		return "index";
	}
	
	@RequestMapping (value="/getgags", method=RequestMethod.GET)
	public String getgags() {
		return "getGags";
	}
}
