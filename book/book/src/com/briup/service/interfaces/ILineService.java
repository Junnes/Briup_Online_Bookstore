package com.briup.service.interfaces;

import java.util.Set;

import com.briup.bean.Line;
import com.briup.common.exception.LineException;

public interface ILineService {

	public void saveLines(Set<Line> lines) throws LineException;
	
	public void removeLines(Long orderId) throws LineException;
	
	public Set<Line> findLinesByOrderId(Long orderId) throws LineException;
}
