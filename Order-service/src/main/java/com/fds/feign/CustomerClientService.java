package com.fds.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.fds.dto.CustomerDTO;

@FeignClient(name="customer-service",url="http://localhost:1111")
public interface CustomerClientService {
	
//	@GetMapping("customers/")
//	public CustomerDTO get
}
