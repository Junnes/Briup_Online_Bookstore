package com.briup.dao;

import java.util.List;

import com.briup.bean.Book;

public interface BookMapper {

	public List<Book> listBook(int param1, int param2);
	
	public Book findBookById(Long id);
	
	public long findTotalCount();
	
	public List<Book> findBookByName(String name);
}
