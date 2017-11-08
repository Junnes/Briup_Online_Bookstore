package com.briup.web.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.Book;
import com.briup.bean.PageBean;
import com.briup.common.exception.BookException;
import com.briup.service.interfaces.IBookService;
import com.wangzh.util.ServiceFactory;


public class ShowBookFilter implements Filter {


	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp= (HttpServletResponse)response;
		String currentPageStr = request.getParameter("currentPage");
		Integer currentPage = null;
		//默认第一次访问为第一页的数据
		if(currentPageStr == null){
			currentPage = 1;
		}else{
			try {
				//获取前台传来的页面为第几页
				currentPage = Integer.parseInt(currentPageStr);
			} catch (NumberFormatException e) {
				resp.sendRedirect("/book/error.jsp");
				return;
			}
		}
		//自定义每页显示2条数据
		int currentCount = 2;
		//调用service层方法，查找出指定页面要显示的书籍集合
		IBookService service = (IBookService) ServiceFactory.getService(IBookService.class);
		PageBean pageBean = null;
		String bookName = request.getParameter("bookName");
		if(bookName != null && !"".equals(bookName.trim())){
			//当前台通过搜索框查找指定书籍时，只将符合搜索要求的书籍显示出来
			try {
				List<Book> bookList = service.findBookByName(bookName);
				//将查询出来的书籍，根据前台传来的页码进行分页显示
				long totalCount = bookList.size();
				long totalPage = (long) Math.ceil((totalCount*1.0 / currentCount*1.0));
				List<Book> subList = null;
				if(totalCount <= currentCount){
					//当查询出来的书籍总数小于每页要显示的数目，则只以一页显示所有书籍
					subList = bookList;
				}else{
					//否则进行分页显示
					int fromIndex = (currentPage-1)*currentCount;
					int endIndex = currentPage*currentCount;
					//如果为最后一页，显示的数量是list.size() - 前面所有页面显示的数量
					if(currentPage == totalPage){
						endIndex = (int) totalCount;
					}
					subList = bookList.subList(fromIndex,endIndex);
				}
				pageBean = new PageBean(totalCount, currentCount, totalPage, currentPage, subList);
				//将bookName存入request中，接下来在页面中分页显示，显示的是根据书名关键字查找出来的书籍
				request.setAttribute("bookName", bookName);
			} catch (BookException e) {
				//捕获到异常，不让其访问该资源，而是跳向一个错误页面
				resp.sendRedirect("/error.jsp");
				return;
			}
		}else{
			//当前台没有向输入框输入值查找书籍是，则将所有书籍查找出来显示
			try {
				pageBean = service.listAllBooks(currentPage,currentCount);
			} catch (BookException e) {
				//捕获到异常，不让其访问该资源，而是跳向一个错误页面
				resp.sendRedirect("/error.jsp");
				return;
			}
		}
		//将数据带到要访问的资源中去
		request.setAttribute("pageBean", pageBean);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
