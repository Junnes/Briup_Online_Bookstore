package com.briup.common.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisSqlSessionFactory {
	private static SqlSessionFactory factory;
	
	private static SqlSessionFactory getSqlSessionFactory(){
		if(factory == null){
			try {
				InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
				factory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return factory;
	}
	
	public static SqlSession openSession(boolean autoCommit){
		return getSqlSessionFactory().openSession(autoCommit);
	}
	
	public static SqlSession openSession(){
		return openSession(false);
	}
}
