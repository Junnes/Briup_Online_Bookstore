package com.briup.web.servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.Line;
import com.briup.common.exception.LineException;
import com.briup.service.interfaces.ILineService;
import com.wangzh.util.ServiceFactory;

public class FindLinesByOrderIdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		Set<Line> lineSet = null;
		try {
			lineSet = service.findLinesByOrderId(orderId);
		} catch (LineException e) {
			//捕获到异常，跳至错误提示页面
			response.sendRedirect(request.getContextPath() + "/error.jsp");
			return;
		}
		if(lineSet != null){
			//成功查找出所有订单项后，存放入request域中，并跳至orderinfo.jsp
			request.setAttribute("lineSet", lineSet);
			request.getRequestDispatcher("orderinfo.jsp").forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}