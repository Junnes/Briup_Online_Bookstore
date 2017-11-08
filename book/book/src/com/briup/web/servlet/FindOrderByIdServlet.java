package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.Order;
import com.briup.common.exception.OrderException;
import com.briup.service.interfaces.IOrderService;
import com.wangzh.util.ServiceFactory;

public class FindOrderByIdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		Long id = null;
		if(idStr != null){
			try {
				//发生转换异常时，结束查询，跳至错误提示页面
				id = Long.parseLong(idStr);
			} catch (NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/error.jsp");
				return;
			}
		}
		IOrderService service = (IOrderService) ServiceFactory.getService(IOrderService.class);
		Order order = null;
		try {
			order = service.findById(id);
		} catch (OrderException e) {
			//捕获到异常，跳至错误提示页面
			response.sendRedirect(request.getContextPath() + "/error.jsp");
			return;
		}
		if(order != null){
			//成功查找到order后跳到查找订单项的servlet
			request.setAttribute("order", order);
			request.getRequestDispatcher("findLinesByOrderId").forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}