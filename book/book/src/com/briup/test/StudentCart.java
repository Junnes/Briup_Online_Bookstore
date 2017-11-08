package com.briup.test;

import java.io.Serializable;

public class StudentCart implements Serializable{
	private Student student;
	public StudentCart() {
		student = new Student("john", 22);
	}
	public void print(){
		System.out.println(student);
	}
}
