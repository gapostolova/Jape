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
	

//	private static final String FILE_PATH = ("D:\\pics\\");

	private static final String FILE_PATH = ("/Users/user-05/Desktop/pics/");


	@RequestMapping (value="/image/{gagID}", method=RequestMethod.GET)
	@ResponseBody
	//remove throws!!!!!!!!!
	public void getImage(Model viewModel, @PathVariable("gagID") String gagID, HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException, IOException {
		UserDAO.getInstance().getAllUsers();

		Long id = Long.parseLong(gagID);
		File file = new File(FILE_PATH + GagDAO.getInstance().getAllGags().get(id).getGag());
		Files.copy(file.toPath(), response.getOutputStream());
	}
	
	
	@RequestMapping (value="/commentPic/{picName}/{type}", method=RequestMethod.GET)
	@ResponseBody
	//remove throws!!!!!!!!!
	public void getPic(Model viewModel, @PathVariable("picName") String picName, @PathVariable("type") String picType, HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException, IOException {
		UserDAO.getInstance().getAllUsers();

		String name = picName.toString();
		String type = picType.toString();
		String picture = name+ "." + type;
		System.out.println(type);
		System.out.println("************* picname: "+picture+"*************************");
		File file = new File(FILE_PATH + picture);
		Files.copy(file.toPath(), response.getOutputStream());
	}
}
