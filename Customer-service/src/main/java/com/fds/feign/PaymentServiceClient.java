package com.fds.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fds.dto.PaymentRequest;
import com.fds.dto.PaymentResponse;

@FeignClient(name = "payment-service", url = "http://localhost:4444")
public interface PaymentServiceClient {
	
	@PostMapping("/payment/process")
	public PaymentResponse processPayment(@RequestBody PaymentRequest request);

}
