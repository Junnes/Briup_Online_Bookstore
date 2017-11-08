package com.briup.dao;

import java.util.List;

import com.briup.bean.Order;

public interface OrderMapper {

	public void saveOrder(Order order);
	
	public List<Order> findOrdersByCustomerId(Long id);
	
	public void deleteOrderById(Long id);
	
	public Order findOrderById(Long id);
	
	public void updateOrderStatus(Order order);
	
	public Order findOrderWithCustomerAndLineById(Long id);
}
