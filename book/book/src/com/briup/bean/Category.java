package com.briup.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String description;
	private Set<Book> books = new HashSet<>(0);
	
	
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
