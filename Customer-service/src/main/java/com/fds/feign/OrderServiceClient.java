package com.fds.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fds.dto.OrderDTO;
import com.fds.dto.OrderRequestDTO;

@FeignClient(name = "order-service", url = "http://localhost:3333")
public interface OrderServiceClient {
	
	@PostMapping("/order/place")
	public OrderDTO placeOrder(@RequestBody OrderRequestDTO request);
	
}
