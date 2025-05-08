package com.fds.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fds.dto.DeliveryDTO;

@FeignClient(name = "delivery-service", url = "http://localhost:5555")
public interface DeliveryServiceClient {
	
	@GetMapping("/delivery/{id}")
	DeliveryDTO getDelivery(@PathVariable("id") Long id);

}
