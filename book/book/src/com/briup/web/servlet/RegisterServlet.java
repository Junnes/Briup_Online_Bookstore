package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.Customer;
import com.briup.common.exception.CustomerException;
import com.briup.service.interfaces.ICustomerService;
import com.wangzh.util.ServiceFactory;

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取页面传来的注册信息，封装成一个Customer对象
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String zip = request.getParameter("zip");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		Customer customer = new Customer(name, password, zip, address, telephone, email);
		//将数据传递给service层进行处理
		ICustomerService service = (ICustomerService) ServiceFactory.getService(ICustomerService.class);
		try {
			//先通过name判断数据库是否有该数据，如果有的话，则不能注册，跳回页面并显示
			Customer oldCustomer = service.findCustomerByName(name);
			if(oldCustomer != null){
				request.getSession().setAttribute("customerExist", "用户名已存在");
				response.sendRedirect(request.getContextPath() + "/register.jsp");
				return;
			}
			service.register(customer);
		} catch (CustomerException e) {
			//捕获到service抛出的异常，跳回注册页面，显示注册失败
			request.getSession().setAttribute("failRegister", "true");
			//request.getRequestDispatcher(request.getContextPath() + "/register.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath() + "/register.jsp");
			return;
		}
		//注册成功重定向到登陆页面,并将本次注册的用户名传递到登陆页面进行显示
		response.sendRedirect(request.getContextPath() + "/login.jsp?name=" 
		+ new String(name.getBytes("utf-8"),"iso8859-1"));//将参数转换成ISO8859-1可识别的字符， 因为 tomcat容器默认以ISO8859-1编码格式对URL编码，
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
