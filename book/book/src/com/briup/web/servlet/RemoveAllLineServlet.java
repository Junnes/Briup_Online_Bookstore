package com.briup.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.ShopCart;

public class RemoveAllLineServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShopCart shopcart = (ShopCart) request.getSession().getAttribute("shopcart");
		if(shopcart != null){
			//清空购物车，即清除所有Line
			shopcart.clear();
			response.sendRedirect(request.getContextPath() + "/user/shopcart.jsp");
		}else{
			//否则跳向失败页面
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}