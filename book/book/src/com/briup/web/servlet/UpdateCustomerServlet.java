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

public class UpdateCustomerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取页面传来的修改信息，封装成一个Customer对象
		Long id = null;
		String idStr = request.getParameter("id");
		if(idStr != null){
			try {
				id = Long.parseLong(idStr);
			} catch (NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/error.jsp");
				return;
			}
		}
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String zip = request.getParameter("zip");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		Customer customer = new Customer(id, name, password, zip, address, telephone, email, null);
		Customer oldCustomer = (Customer) request.getSession().getAttribute("customer");
		if(oldCustomer == null){
			//session中没有customer，重定向到登陆页面
			response.setCharacterEncoding(request.getContextPath() + "/login.jsp");
			return;
		}
		//如果用户名修改任何信息，则跳回页面显示
		boolean noChangeFlag = oldCustomer.toString().equals(customer.toString());
		if(noChangeFlag){
			request.setAttribute("sucessUpdate", "您未修改任何数据");
			request.getRequestDispatcher("userinfo.jsp").forward(request, response);
			return;
		}
		//将数据传递给service层进行处理
		ICustomerService service = (ICustomerService) ServiceFactory.getService(ICustomerService.class);
		try {
			service.updateCustomer(customer);
		} catch (CustomerException e) {
			//捕获到异常,表示修改数据失败，跳回修改页面提示修改数据失败
			request.setAttribute("failUpdate", e.getMessage());
			request.getRequestDispatcher("userinfo.jsp").forward(request, response);
			return;
		}
		//否则表示修改数据成功，跳到首页
		request.getSession().setAttribute("customer", customer);
		request.setAttribute("sucessUpdate", "数据修改成功");
		request.getRequestDispatcher("userinfo.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}