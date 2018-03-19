package webhoinghi.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import webhoinghi.model.Admin;

public interface AdminDAO extends CrudRepository<Admin, Integer>{
	
	List<Admin> findByUsernameAndPassword(String username,String password);
}
