package com.fds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fds.model.Delivery;
import com.fds.service.DeliveryServiceImpl;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

	@Autowired
	DeliveryServiceImpl service;
	
	@GetMapping("/all") // GET request to fetch all deliveries
    public List<Delivery> getDeliveries() {
        return service.getDeliveries();
    }

    @PostMapping("/status") // POST request to update delivery status
    public Delivery status(@RequestBody Delivery deliveryRequest) {
        return service.status(deliveryRequest);
    }
}
