package com.briup.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.Customer;
import com.briup.estore.bean.Order;
import com.briup.estore.common.exception.OrderException;
import com.briup.estore.service.IOrderService;
import com.briup.estore.service.impl.OrderServiceImpl;

@WebServlet("/user/orderFindAll")
public class OrderFindAllServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IOrderService orderService = new OrderServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Customer customer = 
				(Customer)req.getSession().getAttribute("customer");
		long id = customer.getId();
		String path = "/user/order.jsp";
		try {
			List<Order> orders = orderService.findByCustomerId(id);
			req.setAttribute("orders", orders);
		} catch (OrderException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
}	
