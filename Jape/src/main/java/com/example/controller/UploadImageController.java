package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.annotation.MultipartConfig;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Category;
import com.example.model.Gag;
import com.example.model.User;
import com.example.model.dao.GagDAO;
import com.example.model.dao.UserDAO;

import io.undertow.attribute.RequestMethodAttribute;

@Controller
@SessionAttributes("filename")
@MultipartConfig
public class UploadImageController {
	

//	private static final String FILE_LOCATION = "/Users/user-05/Desktop/pics/";
	private static final String FILE_LOCATION = "D:\\pics\\";


	
	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public String prepareForUpload(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("logged")== null || (boolean) session.getAttribute("logged")==false){
			return "redirect:/index";
		}
		
		try {
		
			ArrayList<String> categories = new ArrayList<>();
			for(Category cat : UserDAO.getInstance().getCategories()) {
				categories.add(cat.getCategoryName());
			}
	 		
			session.setAttribute("categories", categories);
		} catch (SQLException e) {
			//TODO error page
			System.out.println("Error loading categories from DB in controller");
		}
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
			Model model, HttpServletRequest request, HttpSession session) throws IOException{
		try {
			File fileOnDisk = new File(FILE_LOCATION + multiPartFile.getOriginalFilename());
			Files.copy(multiPartFile.getInputStream(), fileOnDisk.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			System.out.println(multiPartFile.getOriginalFilename() + " " + multiPartFile.getSize() + " " + title + " " + userId + " " + nsfw + " " + isPublic  );
			//create gag with requestparams
			Gag newGag = new Gag(multiPartFile.getOriginalFilename(), title, userId, nsfw, isPublic);
			
			
			String[] categories = new String[100];
			categories = request.getParameterValues("category");
			ArrayList<Category> cats = new ArrayList<>();
			
			if(categories != null){
				for (int i = 0; i < categories.length; i++) {
					for(Category cat : UserDAO.getInstance().getCategories()) {
						if(cat.getCategoryName().equals(categories[i])) {
							cats.add(cat);
						}
					}
				}
				newGag.setCategory(cats);
			}
			//insert into DB and collections
			GagDAO.getInstance().addGag(newGag);
			
//			//insert into users gags
//			User u = (User)session.getAttribute("user");
//			u.addGag(newGag);
			
			return "profile";
		} catch (SQLException e) {
			System.out.println("Error uploading image!!!!" + e);
			//error page
		}
		
		return "index";

	}
	
	
	
	@RequestMapping(value="/settings", method=RequestMethod.POST)
	public String settings(@RequestParam("failche") MultipartFile multiPartFile,
			@RequestParam("username") String username,
			@RequestParam("description") String description,
			@RequestParam("password") String pass,
			Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("logged")== null || (boolean) session.getAttribute("logged")==false){
			return "redirect:/index";
		}
		
		
		String password = new String();
		try {
			password = User.hashPassword(pass);
			
		} catch (NoSuchAlgorithmException e1) {
			System.out.println("could not hash password in user controller " + e1.getMessage());
		}
		
		User user = (User) session.getAttribute("user");
		
		if(description.trim().isEmpty() || password.trim().isEmpty() || username.trim().isEmpty()){
			session.setAttribute("settingsChangedMessage", "Invalid data!");
			return "redirect:/settings";
		}
		
		
		if(!user.getPassword().equals(password)){
			session.setAttribute("settingsChangedMessage", "Wrong password!");
			return "redirect:/settings";
		}
		
		String[] nameAndType = multiPartFile.getOriginalFilename().split("\\.");
		
		String nameOfPic = user.getUserId()+"."+nameAndType[nameAndType.length-1];
		
				try {
					File fileOnDisk = new File(FILE_LOCATION + nameOfPic);
					try {
						Files.copy(multiPartFile.getInputStream(), fileOnDisk.toPath(), StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						System.out.println("could not save picture (uploadImageController; settings "+ e.getMessage());
					}
					
					System.out.println(multiPartFile.getOriginalFilename() + " " + multiPartFile.getSize() + " " + username + " " + description + " " + password + " \n"  );
					
					
					//insert into DB and collections
					
					if(!username.equals(user.getUsername())){
						UserDAO.getInstance().changeUsername(username, user);
					}
					if(!description.equals(user.getDescription())){
						UserDAO.getInstance().changeDescription(description, user);
					}
					if(!multiPartFile.isEmpty()){
						UserDAO.getInstance().changeProfilePic(nameOfPic, user);
					}
					
					session.setAttribute("settingsChangedMessage", "Changes saved!");
					return "redirect:/settings";
				} catch (SQLException e) {
					System.out.println("Error uploading image in db in uploadImageController/settings!!!!" + e);
					//error page
				}
				
				return "index";

			}

		
		
	
}
