package com.confproject.confproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="page")
public class Page {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String title;
	private String body;
	
	public Page() {}
	
	public Page(int id, String title, String body) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
