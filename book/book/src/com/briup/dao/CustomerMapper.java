package com.briup.dao;

import com.briup.bean.Customer;

public interface CustomerMapper {
	
	public void addCustmoer(Customer customer); 
	
	public Customer findCustomer(String name,String password);
	
	public Customer findCustomerByName(String name);
	
	public void updateCustomer(Customer customer);
}
