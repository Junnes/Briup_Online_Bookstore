 package com.briup.web.servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Line;
import com.briup.bean.Order;
import com.briup.bean.ShopCart;
import com.briup.common.exception.LineException;
import com.briup.service.interfaces.ILineService;
import com.wangzh.util.ServiceFactory;

public class SaveLinesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取数据,将订单中的每一个订单项都保存到集合中
		Order order = (Order) request.getAttribute("order");
		HttpSession session = request.getSession();
		ShopCart shopcart = (ShopCart) session.getAttribute("shopcart");
		if(order == null || shopcart == null){
			//如果没有订单则跳转到购物车页面
			response.sendRedirect(request.getContextPath() + "/user/shopcart.jsp");
			return;
		}
		Set<Line> lines = order.getLines();
		for (Line line : lines) {
			line.setOrder(order);
		}
		//将这个集合传递到service层，把每一个订单项都存入到数据库中
		ILineService service = (ILineService) ServiceFactory.getService(ILineService.class);
		try {
			service.saveLines(lines);
		} catch (LineException e) {
			//捕获异常，订单项入库失败，跳回页面并显示失败
			response.sendRedirect(request.getContextPath() + "/user/shopcart.jsp");
			return;
		}
		//成功插入数据库之后，要把用户的购物车清空
		shopcart.clear();
		//重定向到order.jsp页面显示已存入数据库的订单
		session.setAttribute("orderSucessMsg", "订单提交成功");
		response.sendRedirect(request.getContextPath() + "/user/order.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
