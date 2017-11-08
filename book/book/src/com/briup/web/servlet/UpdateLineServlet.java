package com.briup.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.ShopCart;

public class UpdateLineServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//通过前台传来的id和num 修改订单项中商品的数量
		ShopCart shopcart = (ShopCart) request.getSession().getAttribute("shopcart");
		String idStr = request.getParameter("id");
		String numStr = request.getParameter("num");
		System.out.println(numStr);
		Integer num = null;
		Long id = null;
		if(numStr != null && idStr != null && shopcart != null){
			//当传来的数据能正确的转成相应类型，调用shopcart中的方法修改数量并跳回shopcart.jsp页面
			try {
				id = Long.parseLong(idStr.trim());
				num = Integer.parseInt(numStr.trim());
				shopcart.update(id, num);
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