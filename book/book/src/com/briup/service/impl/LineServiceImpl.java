package com.briup.service.impl;

import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import com.briup.bean.Line;
import com.briup.common.exception.LineException;
import com.briup.common.util.MybatisSqlSessionFactory;
import com.briup.dao.LineMapper;
import com.briup.service.interfaces.ILineService;

public class LineServiceImpl implements ILineService{

	@Override
	public void saveLines(Set<Line> lines) throws LineException {
		if(lines == null){
			//当存放订单项的集合为null时，抛出自定义异常
			throw new LineException();
		}
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession(true);
		LineMapper mapper = sqlSession.getMapper(LineMapper.class);
		for (Line line : lines) {
			//当订单项中的属性有一个为null时，抛出自定义异常
			if(line.getBook() == null || line.getNum() == null || line.getOrder() == null){
				throw new LineException();
			}
			//将每个订单项都插入数据库
			System.out.println(line.getBook());
			mapper.saveLines(line);
		}
		if(sqlSession != null){
			sqlSession.close();
		}
	}

	@Override
	public void removeLines(Long orderId) throws LineException {
		if(orderId == null){
			//如果orderId为null，抛一个自定义异常
			throw new LineException();
		}
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession(true);
		LineMapper mapper = sqlSession.getMapper(LineMapper.class);
		//删除所有订单项
		mapper.removeLinesByOrderId(orderId);
		if(sqlSession != null){
			sqlSession.close();
		}
	}

	@Override
	public Set<Line> findLinesByOrderId(Long orderId) throws LineException {
		if(orderId == null){
			//如果orderId为null，抛一个自定义异常
			throw new LineException();
		}
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession(true);
		LineMapper mapper = sqlSession.getMapper(LineMapper.class);
		//通过orderId查找所有订单项
		Set<Line> lineSet = mapper.findLinesByOrderId(orderId);
		return lineSet;
	}

	

}
