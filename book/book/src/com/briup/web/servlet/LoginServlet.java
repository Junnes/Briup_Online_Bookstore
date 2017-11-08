package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Customer;
import com.briup.common.exception.CustomerException;
import com.briup.service.interfaces.ICustomerService;
import com.wangzh.util.ServiceFactory;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取前台数据
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		//将数据传递给service层进行处理
		ICustomerService service = (ICustomerService) ServiceFactory.getService(ICustomerService.class);
		Customer customer = null;
		try {
			customer = service.login(name, password);
		} catch (CustomerException e) {
			//捕获到service抛出的异常，表示登陆失败，跳回登陆页面
			session.setAttribute("failLogin", e.getMessage());
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		//登录成功，将customer存入session,重定向到首页
		session.setAttribute("customer", customer);
		session.setAttribute("welcomeLogin", "登录成功，欢迎您购物");
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}