package com.fds.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.fds.dto.OrderDTO;

@FeignClient(name = "order-service", url = "http://localhost:3333")
public interface OrderServiceClient {
	
	@GetMapping("order/list")
	public List<OrderDTO> allOrder();

}
