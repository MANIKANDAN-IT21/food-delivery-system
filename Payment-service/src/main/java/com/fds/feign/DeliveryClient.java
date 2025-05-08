package com.fds.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Delivery-service", url = "http://localhost:5555")
public interface DeliveryClient {
	
	@PostMapping("delivery/assign")
	// Sends a request to assign a delivery agent to an order
    void assignAgentToOrder(@RequestParam Long orderId);

}
