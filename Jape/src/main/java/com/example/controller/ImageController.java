package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.dao.GagDAO;
import com.example.model.dao.UserDAO;

@Controller
public class ImageController {
	
	private static final String FILE_PATH = ("C:\\Users\\User\\Desktop\\IT Talents\\Jape\\Jape\\src\\main\\webapp\\WEB-INF\\views\\pics\\");

	@RequestMapping (value="/image/{gagID}", method=RequestMethod.GET)
	@ResponseBody
	//remove throws!!!!!!!!!
	public void getImage(Model viewModel, @PathVariable("gagID") String gagID, HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException, IOException {
		UserDAO.getInstance().getAllUsers();
		
		System.out.println(gagID);
		System.out.println("=========================================/n=========");
		Long id = Long.parseLong(gagID);
		File file = new File(FILE_PATH + GagDAO.getInstance().getAllGags().get(id).getFullName());
		Files.copy(file.toPath(), response.getOutputStream());
	}
}
