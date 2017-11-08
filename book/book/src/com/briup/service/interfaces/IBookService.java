package com.briup.service.interfaces;

import java.util.List;

import com.briup.bean.Book;
import com.briup.bean.PageBean;
import com.briup.common.exception.BookException;

/**
 * 
 * Copyright: Copyright (c) 2017 wangzh
 * 
 * @see: IUserService.java
 * @Description: 用户管理Serivice
 *
 * @version: v1.0.0
 * @author: wangzh
 * @date: 2017年9月22日 上午11:08:21 

 */
public interface IBookService {
	public PageBean listAllBooks(int currentPage, int currentCount) throws BookException ;
	public Book findById(Long id) throws BookException;
	public List<Book> findBookByName(String name) throws BookException ;
}
