package com.briup.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * 实现HttpSessionBindingListener接口，监听session对该对象
 * 放置和移除，分别触发保存和读取事件
 *
 */
public class ShopCart implements Serializable,HttpSessionBindingListener{
	private static final long serialVersionUID = 1L;
	
	// key 商品id value 订单项（商品，数量）
	private Map<Long, Line> map;
	
	public ShopCart() {
		map = new HashMap<Long, Line>();
	}

	/**
	 * 向购物车中添加产品 先判断购物车中有没有该产品的订单项目，如果有，在数量上做改变， 如果没有向map中添加
	 * 
	 * @param line
	 */
	public void add(Line line) {
		if (map.containsKey(line.getBook().getId())) {
			Line oldline = map.get(line.getBook().getId());
			oldline.setNum(oldline.getNum() + 1);
		} else {
			line.setNum(1);
			map.put(line.getBook().getId(), line);
		}
	}

	/**
	 * 删除订单
	 */
	public void delete(Long bookId) {
		map.remove(bookId);
	}
	/**
	 * 获取价钱
	 */
	public double getCost() {
		Set<Long> keySet = map.keySet();
		Iterator<Long> iter = keySet.iterator();
		double cost = 0.0;
		while (iter.hasNext()) {
			Long key = iter.next();
			Line value = map.get(key);
			Integer num = value.getNum();
			double price = value.getBook().getPrice();
			double lineCost = num * price;
			cost += lineCost;
		}
		return cost;
	}

	/**
	 * 获取购物车中所有订单项 List<Line>
	 */
	public Map<Long, Line> getMap() {
		return map;
	}

	/**
	 * 清空购物车
	 */
	public void clear() {
		map.clear();
	}

	/**
	 * 修改指定line中的数量
	 */
	public void update(long key, int num) {
		Line line = map.get(key);
		if (line != null) {
			line.setNum(num);
		}
	}
	
	public int getSize() {
		return map.size();
	}
	/**
	 * 用户上线之后，将用户上一次放置在购物车中的订单项从本地文件中读取出来
	 **/
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		/*HttpSession session = event.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String attributeName = event.getName(); 
		if("shopcart".equals(attributeName) && customer != null){
			String name = customer.getName();
			//根据用户的名字，去读取指定的，独属于该用户的文件
			String filePath = ShopCart.class.getResource("backup").toString();
			filePath = filePath.substring(filePath.indexOf("/") + 1);
			File file = new File(filePath,name + "_shopcart");
			ObjectInputStream ois = null;
			try {
				if(file.exists()){
					ois = new ObjectInputStream(new FileInputStream(file));
					ShopCart shopcart = (ShopCart) ois.readObject();
					//将读取到的订单项与当前对象中的订单项合并到一起
					this.getMap().putAll(shopcart.getMap());
					//读取后删除掉文件
					System.out.println(file.exists());
					file.delete();
					System.out.println(file.delete());
					System.out.println("文件读取成功。。");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}finally {
				if(ois != null){
					try {
						ois.close();
					} catch (IOException e) {
						ois = null;
					}
				}
			}
		}  */
		
	}
	/**
	 * 当用户退出时，将用户购物车中尚未下单的订单项用流
	 * 保存到本地文件
	 **/
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		/*HttpSession session = event.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String attributeName = event.getName();
		if("shopcart".equals(attributeName) && customer != null){
			String name = customer.getName();
			String filePath = ShopCart.class.getResource("backup").toString();
			filePath = filePath.substring(filePath.indexOf("/") + 1);
			File file = new File(filePath, name + "_shopcart");
			//用对象流将该对象保存到本地文件中去，文件名以顾客的 名字_shopcart 命名
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(this);
				oos.flush();
				System.out.println("文件保存成功。。");
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}finally {
				if(oos != null){
					try {
						oos.close();
					} catch (IOException e) {
						oos = null;
					}
				}
			}
		} */
	}

}
