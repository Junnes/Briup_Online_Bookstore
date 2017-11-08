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

public class CheckNameServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		//当name为null 或 空字符串时，不进行处理，终止判断
		if(name == null || "".equals(name.trim())){
			return;
		}
		ICustomerService service = (ICustomerService) ServiceFactory.getService(ICustomerService.class);
		Customer customer = null;
		try {
			customer = service.findCustomerByName(name);
		} catch (CustomerException e) {
			//捕获到异常，终止处理，不枉前台返回数据
			return;
		}
		String data = null;
		if(customer != null){
			//如果有返回值不为null,说明用户名已存在
			data = "{\"flag\":\"true\"}";
		}else{
			//否则用户名可用
			data = "{\"flag\":\"false\"}";
		}
		response.getWriter().write(data);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
