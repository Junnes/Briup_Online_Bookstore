package com.briup.test;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import com.briup.bean.Book;
import com.briup.bean.Line;
import com.briup.bean.Order;
import com.briup.common.util.MybatisSqlSessionFactory;
import com.briup.dao.BookMapper;
import com.briup.dao.LineMapper;
import com.briup.dao.OrderMapper;

public class Test {
	
	public void print(){
		String filePath = this.getClass().getClassLoader().getResource("com/briup/bean/backup").toString();
		filePath = filePath.substring(filePath.indexOf("/") + 1) + "/john_shopcart";
		System.out.println(filePath);
		File file = new File(filePath);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(file.delete());
//		System.out.println("is directory ? " + file.isDirectory());
//		System.out.println("delete " + file.delete());
	}
	
	public static void main(String[] args) {
		/*SqlSession session = MybatisSqlSessionFactory.openSession();
		CustomerMapper mapper = session.getMapper(CustomerMapper.class);
		Customer customer = new Customer();
		customer.setName("tom");
		customer.setPassword("123");
		mapper.addCustmoer(customer);
		session.commit();*/
		/*SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		BookMapper mapper = sqlSession.getMapper(BookMapper.class);
		Book book = mapper.findBookById((long) 1);
		System.out.println(book);*/
		//StudentCart cart = new StudentCart();
		/*try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/com/briup/test/a"));
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/com/briup/test/a"));
//			oos.writeObject(null);
			try {
				Object object = ois.readObject();
				System.out.println(object);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		/*SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
		List<Order> list = mapper.findOrdersByCustomerId((long)4);
		for (Order order : list) {
			System.out.println(order.getId());
		}*/
		/*SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		LineMapper mapper = sqlSession.getMapper(LineMapper.class);
		Set<Line> set = mapper.findLinesByOrderId((long)9);
		for (Line line : set) {
			System.out.println(line.getBook());
		}*/
		/*SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		BookMapper mapper = sqlSession.getMapper(BookMapper.class);
		Book book = mapper.findBookById((long)1);
		System.out.println(book.getCategory().getName());*/
//		new Test().print();
		/*long totalCount = 7;
		int currentCount = 2;
		long totalPage = (long) Math.ceil(((double)totalCount / (double)currentCount));
		System.out.println(totalPage);
		System.out.println(Math.ceil(((double)totalCount / (double)currentCount)));*/
		/*SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		BookMapper mapper = sqlSession.getMapper(BookMapper.class);
		List<Book> listBook = mapper.findBookByName("%J%");
		for (Book book : listBook) {
			System.out.println(book);
		}*/
		/*String name = "john";
		String temp = "%";
		for (int i = 0; i < name.length(); i++) {
			temp += name.charAt(i) + "%";
		}
		System.out.println(temp);*/
		/*List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		System.out.println(list.get(0));
		List<String> subList = list.subList(1, 2);
		List<String> subList1 = list.subList(2, 4);
		for (String string : subList) {
			System.out.print(string+"-----");
		}
		System.out.println();
		for (String string : subList1) {
			System.out.print(string+"-----");
		}*/
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		BookMapper mapper = sqlSession.getMapper(BookMapper.class);
		Book book = mapper.findBookById(1L);
		System.out.println(book);
	}
}










