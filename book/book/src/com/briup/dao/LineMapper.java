package com.briup.dao;

import java.util.Set;

import com.briup.bean.Line;

public interface LineMapper {

	public void saveLines(Line line);
	
	public void removeLinesByOrderId(Long orderId);
	
	public Set<Line> findLinesByOrderId(Long orderId);
}
