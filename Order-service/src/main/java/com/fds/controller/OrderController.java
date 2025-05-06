package com.fds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fds.model.Order;
import com.fds.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService service;

	@PostMapping
	public Order placeOrder(@RequestBody Order order) {
		return service.placeOrder(order);
	}

	@GetMapping("/{id}")
	public Order getOrder(@PathVariable Long id) {
		return service.getOrderById(id);
	}

	@PutMapping("/{id}/status")
	public Order updateStatus(@PathVariable Long id, @RequestParam String status) {
		return service.updateStatus(id, status);
	}

	@GetMapping("/list")
	public List<Order> allOrder() { 
	    return service.allOrder();
	}
}
