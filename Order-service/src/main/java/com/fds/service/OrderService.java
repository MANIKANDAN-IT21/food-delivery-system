package com.fds.service;

import java.util.List;

import com.fds.model.Order;

public interface OrderService {
//	public abstract String saveOrder(Order order);
//	
//	public abstract String updateOrder(Order order);
//	
//	public abstract String deleteOrder(Order order);
//	
//	public abstract List<Order> allOrder();
//	
//	public abstract List<Order> getOrderBy();
	
	 Order placeOrder(Order order);
	 
	Order getOrderById(Long id);
	List<Order> allOrder();
	 
	Order updateStatus(Long id, String status);
}
