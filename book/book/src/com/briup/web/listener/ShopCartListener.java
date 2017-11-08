package com.briup.web.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.briup.bean.ShopCart;

public class ShopCartListener implements HttpSessionAttributeListener {

    public ShopCartListener() {
        // TODO Auto-generated constructor stub
    }

    public void attributeRemoved(HttpSessionBindingEvent se)  { 
		String name = se.getName();
		System.out.println("删除session属性：" + name + ", 值为：" + se.getValue());
    }

    public void attributeAdded(HttpSessionBindingEvent se)  { 
         String name = se.getName();
         if("customer".equals(name)){
        	 se.getSession().setAttribute("shopcart", new ShopCart());
         }
         System.out.println("新建session属性：" + name + ", 值为：" + se.getValue());
    }

    public void attributeReplaced(HttpSessionBindingEvent se)  { 
    	System.out.println("HttpSessionBindingEvent.....");
         HttpSession session = se.getSession();
         String name = se.getName();
         Object oldValue = se.getValue();
         System.out.println("修改session属性：" + name + ", 原值：" + oldValue + ", 新值：" + session.getAttribute(name));
         
    }
	
}
