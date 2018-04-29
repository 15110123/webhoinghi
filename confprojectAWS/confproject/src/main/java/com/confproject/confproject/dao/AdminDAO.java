package com.confproject.confproject.dao;

import com.confproject.confproject.model.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminDAO extends CrudRepository<Admin, Integer> {
	
	List<Admin> findByUsernameAndPassword(String username, String password);
}
