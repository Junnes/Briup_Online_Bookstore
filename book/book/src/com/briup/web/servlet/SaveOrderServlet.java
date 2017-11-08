package com.briup.web.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Customer;
import com.briup.bean.Line;
import com.briup.bean.Order;
import com.briup.bean.ShopCart;
import com.briup.common.exception.OrderException;
import com.briup.service.interfaces.IOrderService;
import com.wangzh.util.ServiceFactory;

public class SaveOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取数据,封装成一个Order对象
		String payment = request.getParameter("payment");
		Date orderDate = new Date();
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		ShopCart shopcart = (ShopCart) session.getAttribute("shopcart");
		if(customer == null){
			//当session中没有用户的数据,重定向到登录页面
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		if(shopcart == null){
			//当session中没有购物车,重定向到购物车页面
			response.sendRedirect(request.getContextPath() + "/user/shopcart.jsp");
			return;
		}
		Set<Line> lines = new HashSet<>();
		Double cost = shopcart.getCost();
		Collection<Line> values = shopcart.getMap().values();
		lines.addAll(values);
		//将数据传递到service层
		Order order = new Order(cost, orderDate, payment,customer,lines,0);
		IOrderService service = (IOrderService) ServiceFactory.getService(IOrderService.class);
		try {
			service.confirmOrder(order);
		} catch (OrderException e) {
			//捕获异常，说明订单入库失败，跳回提交订单页面，显示提交失败
			session.setAttribute("orderFail", e.getMessage());
			response.sendRedirect(request.getContextPath() + "/shopcart.jsp");
			return;
		}
		//订单成功入库后，内部跳转，准备为该订单中的订单项入库
		request.setAttribute("order",order);
		request.getRequestDispatcher("/user/saveLines").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}