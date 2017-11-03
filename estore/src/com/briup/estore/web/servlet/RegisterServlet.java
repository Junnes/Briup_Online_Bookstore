package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.Customer;
import com.briup.estore.common.exception.CustomerException;
import com.briup.estore.service.ICustomerService;
import com.briup.estore.service.impl.CustomerServiceImpl;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ICustomerService customerService = new CustomerServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接收参数,并转化类型(如果需要的话)
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		String zip = req.getParameter("zip");
		String telephone = req.getParameter("telephone");
		String email = req.getParameter("email");
		
		//封装对象(如果需要的话)
		Customer customer = new Customer();
		customer.setName(name);
		customer.setPassword(password);
		customer.setAddress(address);
		customer.setZip(zip);
		customer.setTelephone(telephone);
		customer.setEmail(email);
		
		
		//把封装好的对象交给service层进行业务逻辑处理(如果需要的话)
		String path = "";
		String msg = "";
		try {
			customerService.register(customer);
			//如果service层没有抛出异常,说明注册成功,跳转到login.jsp中
			path = "/login.jsp";
			msg = "注册成功,请登录";
		} catch (CustomerException e) {
			e.printStackTrace();
			//如果service层抛出异常,说明注册失败,跳转到register.jsp中
			path = "/register.jsp";
			msg = "注册失败:"+e.getMessage();
		}
		req.getSession().setAttribute("msg", msg);
		resp.sendRedirect(req.getContextPath()+path);
		//req.getRequestDispatcher(path).forward(req, resp);
	}
	
}
