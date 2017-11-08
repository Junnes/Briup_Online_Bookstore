package com.briup.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.Book;
import com.briup.common.exception.BookException;
import com.briup.service.interfaces.IBookService;
import com.wangzh.util.ServiceFactory;

public class FindBookByIdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取前台传来的数据
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
		//将数据传递给service层，通过id去数据库中查找是否有对应的数据
		IBookService service = (IBookService) ServiceFactory.getService(IBookService.class);
		Book book = null;
		try {
			book = service.findById(id);
		} catch (BookException e) {
			//捕获到异常，跳回首页
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
		//成功查找到书籍，调至productDetail.jsp
		request.setAttribute("book", book);
		request.getRequestDispatcher("/productDetail.jsp").forward(request, response);
}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}