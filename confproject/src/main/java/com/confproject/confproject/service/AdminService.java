package com.confproject.confproject.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.confproject.confproject.dao.AdminDAO;
import com.confproject.confproject.model.Admin;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AdminService  {
	private final AdminDAO admindao;

	public AdminService(AdminDAO adminDAO) {
		this.admindao = adminDAO;
	}
	
	public List<Admin> findAll(){
		List<Admin> admins = new ArrayList<>();
		for(Admin admin : admindao.findAll()){
			admins.add(admin);
		}
		return admins;
	}
	
	public boolean validatelogin(String username, String password){
		List<Admin> admins = new ArrayList<>();
		for(Admin admin : admindao.findByUsernameAndPassword(username, password)){
			admins.add(admin);
		}
		if (admins.isEmpty() == true) return false;
		return true;
	}
	

}
