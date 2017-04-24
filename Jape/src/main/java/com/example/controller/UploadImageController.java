package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Gag;
import com.example.model.dao.GagDAO;

import io.undertow.attribute.RequestMethodAttribute;

@Controller
@SessionAttributes("filename")
@MultipartConfig
public class UploadImageController {
	
	private static final String FILE_LOCATION = "D:\\pics\\";

	
	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public String prepareForUpload() {
		return "upload";
	}
	

	@RequestMapping(value="/upload", method=RequestMethod.POST)

	//request params for all fields
	//put userId in form in jsp!!!!!!!!
	public String receiveUpload(@RequestParam("failche") MultipartFile multiPartFile,
			@RequestParam("title") String title,
			@RequestParam("userId") Long userId,
			@RequestParam("nsfw") Boolean nsfw,
			@RequestParam("isPublic") Boolean isPublic,
			Model model) throws IOException{
		try {
			File fileOnDisk = new File(FILE_LOCATION + multiPartFile.getOriginalFilename());
			Files.copy(multiPartFile.getInputStream(), fileOnDisk.toPath(), StandardCopyOption.REPLACE_EXISTING);
			//create gag with requestparams
			Gag newGag = new Gag(multiPartFile.getOriginalFilename(), title, userId, nsfw, isPublic);
			//insert into DB and collections
			GagDAO.getInstance().addGag(newGag);
			
			return "profile";
		} catch (SQLException e) {
			System.out.println("Error uploading image!!!!" + e);
			//error page
		}
		
		return "";

	}
}
