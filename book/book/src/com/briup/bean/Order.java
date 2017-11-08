package com.briup.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 订单
 * */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	/**
	 * 价钱（总价）
	 * */
	private Double cost;
	private Date orderDate;
	private String payment;
	
	/**
	 * 关联关系 - 一对多 : 对应多个订单项
	 * */
	private Set<Line> lines;
	/**
	 * 关联关系 - 多对一 : 对应一个顾客
	 * */
	private Customer customer;
	private Integer status;
	
	public Order(){
		
	}
	public Order(Double cost, Date orderDate,String payment,Customer customer,Set<Line> lines,Integer status) {
		this.status = status;
		this.cost = cost;
		this.orderDate = orderDate;
		this.payment = payment;
		this.customer = customer;
		this.lines = lines;
	}
	
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Set<Line> getLines() {
		return lines;
	}
	public void setLines(Set<Line> lines) {
		this.lines = lines;
	}
	public Customer getCustomer() {
		return customer;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
