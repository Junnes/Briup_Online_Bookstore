package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.common.exception.LineException;
import com.briup.service.interfaces.ILineService;
import com.wangzh.util.ServiceFactory;

public class RemoveLinesByOrderIdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//删除订单之前，先要把该订单中的所有订单项删除
		String idStr = request.getParameter("id");
		Long orderId = null;
		if(idStr != null){
			try {
				//发生转换异常时，结束查询，跳至错误提示页面
				orderId = Long.parseLong(idStr);
			} catch (NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/error.jsp");
				return;
			}
		}
		ILineService service = (ILineService) ServiceFactory.getService(ILineService.class);
		try {
			service.removeLines(orderId);
		} catch (LineException e) {
			//捕获到异常，跳至错误提示页面
			response.sendRedirect(request.getContextPath() + "/error.jsp");
			return;
		}
		//删除完该订单中的所有订单项，再跳至删除订单的servlet
		request.getRequestDispatcher("/user/removeOrderById").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
