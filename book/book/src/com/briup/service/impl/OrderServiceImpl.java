package com.briup.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.briup.bean.Order;
import com.briup.common.exception.OrderException;
import com.briup.common.util.MybatisSqlSessionFactory;
import com.briup.dao.OrderMapper;
import com.briup.service.interfaces.IOrderService;

public class OrderServiceImpl implements IOrderService{
	
	@Override
	public void confirmOrder(Order order) throws OrderException {
		if(order.getCost() == null || order.getCustomer() == null ||
					order.getOrderDate() == null || order.getPayment() == null){
			//保证order里面必要属性值不能为空，否则抛一个自定义异常
			throw new OrderException("订单提交失败");
		}
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession(true);
		OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
		mapper.saveOrder(order);
		if(sqlSession != null){
			sqlSession.close();
		}
	}

	@Override
	public void deleteOrder(Long id) throws OrderException {
		if(id == null){
			//如果id为null，抛一个自定义异常
			throw new OrderException();
		}
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession(true);
		OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
		mapper.deleteOrderById(id);
		if(sqlSession != null){
			sqlSession.close();
		}
	}

	@Override
	public Order findById(Long id) throws OrderException {
		if(id == null){
			//如果id为null，抛一个自定义异常
			throw new OrderException();
		}
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession(true);
		OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
		//通过id查找order
		Order order = mapper.findOrderById(id);
		return order;
	}

	@Override
	public List<Order> findByCustomerId(Long id) throws OrderException {
		if(id == null){
			//如果id为null，抛一个自定义异常
			throw new OrderException();
		}
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession(true);
		OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
		List<Order> orderList = mapper.findOrdersByCustomerId(id);
		if(sqlSession != null){
			sqlSession.close();
		}
		return orderList;
	}

	@Override
	public void updateOrderStatus(Order order) throws OrderException {
		if (order == null || order.getStatus() == null || order.getId() == null) {
			throw new OrderException("订单为空");
		}
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession(true);
		OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
		mapper.updateOrderStatus(order);
		sqlSession.commit();
		sqlSession.close();
	}

	@Override
	public Order findOrderWithLineAndBook(Long id) throws OrderException {
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession(true);
		OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
		Order order = mapper.findOrderWithCustomerAndLineById(id);
		sqlSession.close();
		return order;
	}

}
