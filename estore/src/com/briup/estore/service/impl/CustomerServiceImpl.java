package com.briup.estore.service.impl;

import org.apache.ibatis.session.SqlSession;

import com.briup.estore.bean.Customer;
import com.briup.estore.common.MyBatisSqlSessionFactory;
import com.briup.estore.common.exception.CustomerException;
import com.briup.estore.dao.ICustomerDao;
import com.briup.estore.service.ICustomerService;

public class CustomerServiceImpl implements ICustomerService{
	private ICustomerDao customerDao;
	@Override
	public void register(Customer customer) throws CustomerException {
		
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		customerDao = session.getMapper(ICustomerDao.class);
		
		String name = customer.getName();
		if(name!=null){
			Customer c = customerDao.findByName(name);
			//c不为null说明这个用户名被占用了
			if(c!=null){
				throw new CustomerException("用户名被占用");
			}else{
				try {
					customerDao.saveCustomer(customer);
					session.commit();
				} catch (Exception e) {
					e.printStackTrace();
					session.rollback();
					throw new CustomerException("数据操作失败..");
				}
			}
		}
		
	}

	@Override
	public Customer login(String name, String password) throws CustomerException {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		customerDao = session.getMapper(ICustomerDao.class);
		
		Customer c = customerDao.findByName(name);
		if(c==null){
			throw new CustomerException("用户名不存在");
		}
		if(!c.getPassword().equals(password)){
			throw new CustomerException("密码错误");
		}
		
		return c;
	}

	@Override
	public void updateCustomer(Customer customer) throws CustomerException {

		SqlSession session = MyBatisSqlSessionFactory.openSession();
		customerDao = session.getMapper(ICustomerDao.class);
		
		try {
			customerDao.updateCustomer(customer);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
			throw new CustomerException(e.getMessage());
		}
	}

}
