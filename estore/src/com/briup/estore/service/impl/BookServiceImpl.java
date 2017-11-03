package com.briup.estore.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.briup.estore.bean.Book;
import com.briup.estore.common.MyBatisSqlSessionFactory;
import com.briup.estore.common.exception.BookException;
import com.briup.estore.dao.IBookDao;
import com.briup.estore.service.IBookService;

public class BookServiceImpl implements IBookService{
	private IBookDao bookDao;
	@Override
	public List<Book> listAllBooks() throws BookException {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		bookDao = session.getMapper(IBookDao.class);
		
		List<Book> books = bookDao.queryAll();
		
		return books;
	}

	@Override
	public Book findById(Long id) throws BookException {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		bookDao = session.getMapper(IBookDao.class);
		
		Book book = bookDao.queryById(id);
		
		return book;
	}

}
