package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

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

import io.undertow.attribute.RequestMethodAttribute;

@Controller
@SessionAttributes("filename")
@MultipartConfig
public class UploadImageController {
	
	private String vzemiToqImage;

	private static final String FILE_LOCATION = "C:\\Users\\Gabriela\\Desktop\\upload\\";
	
	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public String prepareForUpload() {
		return "upload";
	}
	

	@RequestMapping(value="/image/{fileName}", method=RequestMethod.GET)
	@ResponseBody
	public void prepareForUpload(@PathVariable("fileName") String fileName, HttpServletResponse resp, Model model) throws IOException {
		File file = new File(FILE_LOCATION + vzemiToqImage);
		Files.copy(file.toPath(), resp.getOutputStream());
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String receiveUpload(@RequestParam("failche") MultipartFile multiPartFile, Model model) throws IOException{
		//multiPartFile.getOriginalFilename() -> change to the name i want it to be
		File fileOnDisk = new File(FILE_LOCATION + multiPartFile.getOriginalFilename());
		
		Files.copy(multiPartFile.getInputStream(), fileOnDisk.toPath(), StandardCopyOption.REPLACE_EXISTING);
		////insert name into db 
		vzemiToqImage = multiPartFile.getOriginalFilename();
		model.addAttribute("filename", multiPartFile.getOriginalFilename());
		return "upload";
	}
}
