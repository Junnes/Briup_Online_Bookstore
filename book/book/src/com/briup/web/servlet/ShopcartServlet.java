package com.briup.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Book;
import com.briup.bean.Customer;
import com.briup.bean.Line;
import com.briup.bean.ShopCart;
import com.briup.common.exception.BookException;
import com.briup.service.interfaces.IBookService;
import com.wangzh.util.ServiceFactory;

public class ShopcartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//每次点击添加购物车就生成了一个 订单项（Line对象)
		Line line = new Line();
		//判断session中是否有ShopCart对象，如果没有则创建一个,并把添加到session中
		HttpSession session = request.getSession();
		ShopCart shopcart = (ShopCart) session.getAttribute("shopcart");
		if(shopcart == null){
			shopcart = new ShopCart();
			session.setAttribute("shopcart",shopcart);
		}
		//通过id获取book
		String idStr = request.getParameter("id");
		Long id = null;
		if(idStr != null){
			try {
				id = Long.parseLong(idStr);
			} catch (NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/error.jsp");
				return;
			}
		}
		IBookService service = (IBookService) ServiceFactory.getService(IBookService.class);
		Book book = null;
		try {
			book = service.findById(id);
		} catch (BookException e) {
			//捕获到异常表示没有从数据库中查找到该书籍,跳转至提示页面
			response.sendRedirect(request.getContextPath() + "/error.jsp");
			return;
		}
		if(book != null){
			line.setBook(book);
			//向购物车中添加该订单项,并跳转至shopcart.jsp
			shopcart.add(line);
			//每次点击添加购物车，都将shopcart对象写入本地
			//saveShopCart(request, response);
			response.sendRedirect(request.getContextPath() + "/user/shopcart.jsp");
		}else{
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}  
	/**
	 * 将购物车保存到本地
	 */
	private void saveShopCart(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		ShopCart shopcart = (ShopCart) session.getAttribute("shopcart");
		if(shopcart != null && customer != null){
			String name = customer.getName();
			String filePath = this.getClass().getClassLoader().getResource("com/briup/bean/backup").toString();
			filePath = filePath.substring(filePath.indexOf("/") + 1);
			File file = new File(filePath + "/" + name + "_shopcart");
			//用对象流将该对象保存到本地文件中去，文件名以顾客的 名字_shopcart 命名
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(shopcart);
				oos.flush();
				System.out.println("文件保存成功。。");
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if(oos != null){
					try {
						oos.close();
					} catch (IOException e) {
						oos = null;
					}
				}
			}
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
