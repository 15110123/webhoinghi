package webhoinghi.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import webhoinghi.dao.AdminDAO;
import webhoinghi.dao.NewsDAO;
import webhoinghi.model.News;
import webhoinghi.service.AdminService;
import webhoinghi.service.NewsService;;

@RestController
public class RestHomeController {
	
	@Autowired 
	private AdminService admindao;
	
	@Autowired
	private NewsService newsservice;
	
	@GetMapping("/hello")
	public String hello(){
		return "Hello World!!!";
	}
	
	@GetMapping("/testadmin")
	public boolean validate()
	{
		return admindao.validatelogin("admin", "123456");
	}
	
	

}
