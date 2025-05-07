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

//	@Autowired
//	DeliveryServiceImpl service;
	

//
//    @PostMapping("/status") // POST request to update delivery status
//    public Delivery status(@RequestBody Delivery deliveryRequest) {
//        return service.status(deliveryRequest);
//    }
	
	@Autowired
    private DeliveryService deliveryService;
	
	@Autowired
	private OrderServiceClient orderserviceClient;
	
	@GetMapping("/orders")
	public List<OrderDTO> allOrder(){
		return orderserviceClient.allOrder();
	}
	
	@GetMapping("/all") // GET request to fetch all deliveries
    public List<Delivery> getDeliveries() {
        return deliveryService.getDeliveries();
    }

    @PostMapping("/assign")
    public Delivery assign(@RequestParam Long orderId) {
        return deliveryService.assignAgentToOrder(orderId);
    }

    @PutMapping("/{id}/status")
    public Delivery updateStatus(@PathVariable Long id, @RequestParam String status) {
        return deliveryService.updateDeliveryStatus(id, status);
    }

    @GetMapping("/{id}")
    public Delivery get(@PathVariable Long id) {
        return deliveryService.getDelivery(id);
    }
    
}
