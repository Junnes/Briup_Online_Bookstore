package com.briup.service.impl;

import org.apache.ibatis.session.SqlSession;

import com.briup.bean.Customer;
import com.briup.common.exception.CustomerException;
import com.briup.common.util.MybatisSqlSessionFactory;
import com.briup.dao.CustomerMapper;
import com.briup.service.interfaces.ICustomerService;

public class CustomerServiceImpl implements ICustomerService{
	private SqlSession sqlSession;
	
	private CustomerMapper getMapper(){
		sqlSession = MybatisSqlSessionFactory.openSession(true);
		CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
		return mapper;
	}

	private void closeSession(){
		if(sqlSession != null){
			sqlSession.close();
		}
	}
	@Override
	public void register(Customer customer) throws CustomerException {
		String name = customer.getName();
		String password = customer.getPassword();
		//判断用户名和密码。不满足条件时,抛出自定义异常
		if((name != null && password != null) && (!"".equals(name.trim())
					&& name.length() <= 8 && password.length() >= 6 && password.length() <= 8 )){
			//通过mybatis将数据入库
			CustomerMapper mapper = getMapper();
			mapper.addCustmoer(customer);
			closeSession();
		}else{
			//将自定义异常抛给web层
			throw new CustomerException("用户名不能为空且长度在0-8字符之间，密码不能为空且长度在6-8字符之间");
		}
		
	}

	@Override
	public Customer login(String name, String password) throws CustomerException {
		//如果name或passord为null，抛一个自定义异常
		if(name == null || password == null){
			throw new CustomerException("没有输入用户名或密码");
		}
		//通过mybatis从数据库中查找数据
		CustomerMapper mapper = getMapper();
		Customer customer = mapper.findCustomer(name, password);
		if(customer == null){
			throw new CustomerException("用户名或密码错误");
		}
		closeSession();
		return customer;
	}

	@Override
	public void updateCustomer(Customer customer) throws CustomerException {
		Long id = customer.getId();
		String name = customer.getName();
		String password = customer.getPassword();
		if((id != null && name != null && password != null) && (!"".equals(name.trim())
				&& name.length() <= 8 && password.length() >= 6 && password.length() <= 8 )){
			CustomerMapper mapper = getMapper();
			mapper.updateCustomer(customer);
			closeSession();
		}else{
			throw new CustomerException("输入数据不规范,更新用户失败");
		}
		
	}

	@Override
	public Customer findCustomerByName(String name) throws CustomerException {
		//根据用户名去查找是否有该数据
		CustomerMapper mapper = getMapper();
		Customer customer = mapper.findCustomerByName(name);
		closeSession();
		return customer;
	}

}
