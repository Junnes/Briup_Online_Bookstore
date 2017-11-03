package com.briup.estore.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.briup.estore.bean.Book;
import com.briup.estore.common.MyBatisSqlSessionFactory;

public class Test {
	
	public static void main(String[] args) {
		
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		IBookDao dao = session.getMapper(IBookDao.class);
		List<Book> list = dao.queryAll();
		for(Book b:list){
			System.out.println(b);
		}
		
		/*
		ICustomerDao dao = session.getMapper(ICustomerDao.class);
		Customer c = dao.findByName("tom");
		System.out.println(c);
		*/
		
		/*
		ICustomerDao dao = session.getMapper(ICustomerDao.class);
		Customer c = new Customer();
		c.setName("tom");
		c.setPassword("123456");
		c.setAddress("中国昆山");
		c.setZip("21511");
		c.setTelephone("110");
		c.setEmail("123@qq.com");
		
		dao.saveCustomer(c);
		
		session.commit();
		*/
		
	}
	
}
