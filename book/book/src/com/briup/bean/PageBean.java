package com.briup.bean;

import java.util.List;

public class PageBean {
	/*
	 * 书籍的总数量
	 */
	private Long totalCount;
	/*
	 * 当前页面显示书籍的数量
	 */
	private Integer currentCount;
	/*
	 * 总页数
	 */
	private Long totalPage;
	/*
	 * 当前页面为第几页
	 */
	private Integer currentPage;
	/*
	 * 每页显示书的集合
	 */
	private List<Book> list;
	
	public PageBean() {
		super();
	}
	public PageBean(Long totalCount, Integer currentCount, Long totalPage, Integer currentPage, List<Book> list) {
		super();
		this.totalCount = totalCount;
		this.currentCount = currentCount;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.list = list;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(Integer currentCount) {
		this.currentCount = currentCount;
	}
	public Long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public List<Book> getList() {
		return list;
	}
	public void setList(List<Book> list) {
		this.list = list;
	}
}
