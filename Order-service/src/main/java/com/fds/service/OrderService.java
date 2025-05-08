package com.fds.service;

import java.util.List;

import com.fds.dto.OrderRequestDTO;
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
	
	// Places an order using an OrderRequestDTO
	Order placeOrder(OrderRequestDTO request);

	// Places an order using a direct Order object
	Order placeOrder(Order order);

	// Retrieves an order by its unique ID
	Order getOrderById(Long id);
	
	// Fetches a list of all orders in the system
	List<Order> allOrder();
	
	// Updates the status of an order by its ID
	Order updateStatus(Long id, String status);
}
