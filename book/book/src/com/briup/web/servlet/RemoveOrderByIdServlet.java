package com.briup.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.common.exception.OrderException;
import com.briup.service.interfaces.IOrderService;
import com.wangzh.util.ServiceFactory;

public class RemoveOrderByIdServlet extends HttpServlet {

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
		try {
			service.deleteOrder(id);
		} catch (OrderException e) {
			//捕获到异常，跳至错误提示页面
			response.sendRedirect(request.getContextPath() + "/error.jsp");
			return;
		}
		//成功删除订单后，跳回订单显示页面
		response.sendRedirect(request.getContextPath() + "/user/order.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}