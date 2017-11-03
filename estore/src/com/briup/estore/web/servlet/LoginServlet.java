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

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private ICustomerService customerService = new CustomerServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接收参数,并转化类型(如果需要的话)
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		
		//封装对象(如果需要的话)
		//把封装好的对象交给service层进行业务逻辑处理(如果需要的话)
		String path = "";
		String msg = "";
		try {
			Customer c = customerService.login(name, password);
			path = "/index.jsp";
			req.getSession().setAttribute("customer", c);
		} catch (CustomerException e) {
			e.printStackTrace();
			path = "/login.jsp";
			msg = "登录失败:"+e.getMessage();
			req.getSession().setAttribute("msg", msg);
		}
		
		resp.sendRedirect(req.getContextPath()+path);
		
	}
	
}
