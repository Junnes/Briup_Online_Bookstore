package com.briup.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.ShopCart;

public class RemoveLineServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//通过书的id去删除对应的Line
		ShopCart shopcart = (ShopCart) request.getSession().getAttribute("shopcart");
		String idStr = request.getParameter("id");
		Long id = null;
		if(shopcart != null && idStr != null){
			try {
				//成功执行完delete(bookId)后跳回shopcart.jsp
				id = Long.parseLong(idStr);
				shopcart.delete(id);
				response.sendRedirect(request.getContextPath() + "/user/shopcart.jsp");
			} catch (NumberFormatException e) {
				//否则跳向失败页面
				response.sendRedirect(request.getContextPath() + "/error.jsp");
			}
		}else{
			//如果session中没有购物车，则跳转到shopcart.jsp
			response.sendRedirect(request.getContextPath() + "/user/shopcart.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}