package com.briup.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.junit.Test;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.briup.bean.Line;
import com.briup.bean.Order;
import com.briup.common.exception.OrderException;
import com.briup.common.util.AlipayClientFactory;
import com.briup.service.interfaces.ILineService;
import com.briup.service.interfaces.IOrderService;
import com.google.gson.JsonObject;
import com.wangzh.util.ServiceFactory;

/**
 * Servlet implementation class PayServlet
 */
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PayServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pay = request.getParameter("pay");
		HttpSession session = request.getSession();
		try {
			Method method = this.getClass().getMethod(pay, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("orderSucessMsg", "系统出错");
			request.getRequestDispatcher("user/payway.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void zhifubao(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
		//获取前台订单数据
		String idStr = request.getParameter("orderid");
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
		IOrderService service = (IOrderService) ServiceFactory.getService(IOrderService.class);
		Order order = service.findOrderWithLineAndBook(orderId);
		AlipayClient alipayClient = AlipayClientFactory.getAlipayClient(); // 获得初始化的AlipayClient
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();// 创建API对应的request
		alipayRequest.setReturnUrl("http://127.0.0.1:8998/book/user/order.jsp");
	   //alipayRequest.setNotifyUrl("http:/127.0.0.1:8998/payServlet?paysuccess");//在公共参数中设置回跳和通知地址
		JSONObject json = new JSONObject();
		//业务参数填充
		/* (必选) 商户订单号,64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复 */
		json.put("out_trade_no",System.currentTimeMillis()); //订单编号
		/* (必选) 订单标题 */
		json.put("subject", getOrderName(order));  //订单名字
		/* (必选) 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000] */
		json.put("total_amount", order.getCost()); //订单总价钱
		/* (可选) 销售产品码 */
		json.put("product_code", "FAST_INSTANT_TRADE_PAY"); //产品代码
		/* (可选) 订单描述 */
		json.put("body", "订单付账");
		alipayRequest.setBizContent(json.toString());
		String form = "";
		try {
			form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
			order.setStatus(1);
			service.updateOrderStatus(order);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(form);// 直接将完整的表单html输出到页面
			response.getWriter().flush();
			response.getWriter().close();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() {
		System.out.println(UUID.randomUUID());
	}


	public void weixin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().append("<h1>研发中，请选择支付宝付款<h1>");
		response.getWriter().flush();
	}
	public String getOrderName(Order order) {
		if (order == null || order.getLines().size() <= 0) {
			return UUID.randomUUID().toString();
		}
		StringBuffer buffer = new StringBuffer();
		for (Line line : order.getLines()) {
			buffer.append(line.getBook().getName());
		}
		return buffer.toString();
	}
	
	public void paysuccess(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("---------------");
	}
}
