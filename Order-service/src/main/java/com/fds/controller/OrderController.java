package com.fds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fds.dto.OrderRequestDTO;
import com.fds.model.Order;
import com.fds.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService service;

	@PostMapping("/place")
	public ResponseEntity<Order> placeOrder(@RequestBody @Validated OrderRequestDTO request) {
		// Places a new order using a request DTO and returns a created status
		return new ResponseEntity<>(service.placeOrder(request), HttpStatus.CREATED);
	}

	@PostMapping
	public Order placeOrder(@RequestBody @Validated Order order) {
		// Places an order directly using an Order object
		return service.placeOrder(order);
	}

	@GetMapping("/{id}")
	public Order getOrder(@PathVariable Long id) {
		// Retrieves an order by ID
		return service.getOrderById(id);
	}

	@PutMapping("/{id}/status")
	public Order updateStatus(@PathVariable @Validated Long id, @RequestParam @Validated String status) {
		// Updates the status of an order by ID
		return service.updateStatus(id, status);
	}

	@GetMapping("/list")
	public List<Order> allOrder() {
		// Fetches all orders from the system
		return service.allOrder();
	}
}
