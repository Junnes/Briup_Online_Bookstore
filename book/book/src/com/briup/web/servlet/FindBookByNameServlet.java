package com.briup.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.Book;
import com.briup.common.exception.BookException;
import com.briup.service.interfaces.IBookService;
import com.google.gson.Gson;
import com.wangzh.util.ServiceFactory;

public class FindBookByNameServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取书名关键字
		String bookName = request.getParameter("bookName");
		if(bookName.trim().equals("")){
			//当前台没有输入任何关键字的时候,不执行接下来的查询
			return;
		}
		IBookService service =  (IBookService) ServiceFactory.getService(IBookService.class);
		List<Book> bookList = null;
		try {
			bookList = service.findBookByName(bookName);
		} catch (BookException e) {
			response.sendRedirect(request.getContextPath() + "/error.jsp");
			return;
		}
		//将获取的书籍集合用转换成json格式的数据返回客户端
		Gson gson = new Gson();
		String json = gson.toJson(bookList);
		response.getWriter().write(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}