package com.fds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fds.model.Order;
import com.fds.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
    private OrderRepository repository;


	public Order placeOrder(Order order) {
		order.setStatus("Pending");
        return repository.save(order);
	}

	public Order getOrderById(Long id) {
		 return repository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
	}
	
	public Order updateStatus(Long id, String status) {
		Order order = getOrderById(id);
        order.setStatus(status);
        return repository.save(order);
	}

	public List<Order> allOrder() { 
        return repository.findAll();
    }

}
