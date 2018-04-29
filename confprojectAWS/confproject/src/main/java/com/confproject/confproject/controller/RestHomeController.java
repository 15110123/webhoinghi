package com.confproject.confproject.controller;

import com.confproject.confproject.service.AdminService;
import com.confproject.confproject.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
