package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.ShoppingCart;

@WebServlet("/user/shopcartRemove")
public class ShopcartRemoveServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		long id = Long.parseLong(req.getParameter("id"));
		
		ShoppingCart shoppingCart = 
				(ShoppingCart)req.getSession().getAttribute("shoppingCart");
		
		shoppingCart.delete(id);
		
		String path = "/user/shopcart.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
		
		
	}
	
}
