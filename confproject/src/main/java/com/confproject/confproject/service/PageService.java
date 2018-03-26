package com.confproject.confproject.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import com.confproject.confproject.dao.PageDAO;
import com.confproject.confproject.model.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;



@Service
@Transactional
public class PageService {
	
	private final PageDAO pagedao;
	
	public PageService(PageDAO pagedao) {
		this.pagedao = pagedao;
	}
	
	public Page findPage(int id){
		return pagedao.findById(id).get();
	}
	
	public void save(Page page){
		pagedao.save(page);
	}
	
}
