package com.briup.web.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.Customer;
import com.briup.bean.Order;
import com.briup.common.exception.OrderException;
import com.briup.service.interfaces.IOrderService;
import com.wangzh.util.ServiceFactory;

public class ShowOrderFilter implements Filter {

    public ShowOrderFilter() {
        // TODO Auto-generated constructor stub
    }
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//访问order.jsp页面之前进行拦截，先把数据库把订单数据查找出来存在request域中
		HttpServletRequest req = (HttpServletRequest) request;
		Customer customer = (Customer) req.getSession().getAttribute("customer");
		if(customer != null){
			//当用户登录后才能执行订单查询
			IOrderService service = (IOrderService) ServiceFactory.getService(IOrderService.class);
			List<Order> orderList = null;
			try {
				orderList = service.findByCustomerId(customer.getId());
			} catch (OrderException e) {
				//捕获到异常，不让其访问该资源，而是跳向一个错误页面
				HttpServletResponse resp= (HttpServletResponse)response;
				resp.sendRedirect("/error.jsp");
				return;
			}
			if(orderList != null){
				//将数据存入request域中
				request.setAttribute("orderList", orderList);
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
