package com.briup.estore.web.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.briup.estore.bean.ShoppingCart;

@WebListener
public class SessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		ShoppingCart sc = new ShoppingCart();
		
		HttpSession session = se.getSession();
		session.setAttribute("shoppingCart", sc);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
	}

}
