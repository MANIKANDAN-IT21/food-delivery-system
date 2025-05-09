package com.fds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fds.dto.OrderDTO;
import com.fds.feign.OrderServiceClient;
import com.fds.model.Delivery;
import com.fds.service.DeliveryService;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
	
	@Autowired
    private DeliveryService deliveryService;
	
	@Autowired
	private OrderServiceClient orderserviceClient;
	
	@GetMapping("/orders")
	public List<OrderDTO> allOrder(){
		// Calls the order service client to retrieve all orders as DTOs
		return orderserviceClient.allOrder();
	}
	
	@GetMapping("/all") // GET request to fetch all deliveries
    public List<Delivery> getDeliveries() {
		// Retrieves a list of all deliveries from the delivery service
        return deliveryService.getDeliveries();
    }

    @PostMapping("/assign")
    public Delivery assign(@RequestParam Long orderId) {
    	// Assigns a delivery agent to an order using the given order ID
        return deliveryService.assignAgentToOrder(orderId);
    }

    @PutMapping("/{id}/status")
    public Delivery updateStatus(@PathVariable Long id, @RequestParam String status) {
    	// Updates the delivery status for a specific delivery by its ID
        return deliveryService.updateDeliveryStatus(id, status);
    }

    @GetMapping("/{id}")
    public Delivery get(@PathVariable("id") Long id) {
    	// Fetches details of a specific delivery using its ID
        return deliveryService.getDelivery(id);
    }
    
}
