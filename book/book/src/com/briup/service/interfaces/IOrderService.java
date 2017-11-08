package com.briup.service.interfaces;

import java.util.List;

import com.briup.bean.Order;
import com.briup.common.exception.OrderException;

public interface IOrderService {
	public void confirmOrder(Order order) throws OrderException;
	public void deleteOrder(Long id) throws OrderException;
	public Order findById(Long id) throws OrderException;
	public Order findOrderWithLineAndBook(Long id) throws OrderException;
	public  List<Order> findByCustomerId(Long id) throws OrderException;
	public void updateOrderStatus(Order order) throws OrderException;
}
