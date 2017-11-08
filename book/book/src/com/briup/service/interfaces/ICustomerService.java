package com.briup.service.interfaces;

import com.briup.bean.Customer;
import com.briup.common.exception.CustomerException;

public interface ICustomerService {
	public void register(Customer customer) throws CustomerException;
	public Customer login(String name,String password) throws CustomerException;
	public void updateCustomer(Customer customer) throws CustomerException;
	public Customer findCustomerByName(String name) throws CustomerException;
}
