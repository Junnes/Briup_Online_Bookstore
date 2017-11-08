package com.briup.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.briup.bean.Book;
import com.briup.bean.PageBean;
import com.briup.common.exception.BookException;
import com.briup.common.util.MybatisSqlSessionFactory;
import com.briup.dao.BookMapper;
import com.briup.service.interfaces.IBookService;

public class BookServiceImpl implements IBookService{

	@Override
	public PageBean listAllBooks(int currentPage, int currentCount) throws BookException {
		//通过传来当前页码和每页要显示的数量查找指定书籍
		PageBean pageBean = new PageBean();
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		BookMapper mapper = sqlSession.getMapper(BookMapper.class);
		//查找书籍的总数量
		long totalCount = mapper.findTotalCount();
		//当前页要显示的数据，为当前页*每页要显示的数据 - 前面页所有显示的数据
		List<Book> list = mapper.listBook(currentPage*currentCount,(currentPage-1)*currentCount);
		//总页数为书籍总数量除以每页显示的数量并向上取整
		long totalPage = (long) Math.ceil(((double)totalCount / (double)currentCount));
		//为PageBean每个属性赋值
		pageBean.setCurrentCount(currentCount);
		pageBean.setTotalCount(totalCount);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		if(sqlSession != null)sqlSession.close();
		return pageBean;
	}

	@Override
	public Book findById(Long id) throws BookException {
		if(id == null){
			throw new BookException("未查到该本书籍信息");
		}
		//通过订单id查询出订单中的书籍
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		BookMapper mapper = sqlSession.getMapper(BookMapper.class);
		Book book = mapper.findBookById(id);
		if(sqlSession != null)sqlSession.close();
		if(book == null){
			throw new BookException("未查到该本书籍信息");
		}
		return book;
	}

	@Override
	public List<Book> findBookByName(String name) throws BookException  {
		if(name == null){
			throw new BookException("没有输入书名");
		}
		//通过书名关键字模糊查询
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		BookMapper mapper = sqlSession.getMapper(BookMapper.class);
		//拼接成模糊插叙 ----> "%X%X%X%"
		String tempName = "%";
		for (int i = 0; i < name.length(); i++) {
			tempName += name.charAt(i) + "%";
		}
		List<Book> list = mapper.findBookByName(tempName);
		if(sqlSession != null)sqlSession.close();
		return list;
	}

}
