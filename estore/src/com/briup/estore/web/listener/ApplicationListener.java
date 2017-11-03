package com.briup.estore.web.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.briup.estore.bean.Book;
import com.briup.estore.common.exception.BookException;
import com.briup.estore.service.IBookService;
import com.briup.estore.service.impl.BookServiceImpl;

@WebListener
public class ApplicationListener implements ServletContextListener{
	private IBookService bookService = new BookServiceImpl();
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		List<Book> list = null;
		try {
			list = bookService.listAllBooks();
		} catch (BookException e) {
			e.printStackTrace();
		}
		ServletContext application = sce.getServletContext();
		application.setAttribute("books", list);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
