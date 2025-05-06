package com.fds.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fds.model.PaymentRequest;
import com.fds.model.PaymentResponse;
import com.fds.repository.PaymentRepository;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	@Autowired
    private PaymentRepository paymentRepository;
	
	   @PostMapping("/process")
	    public PaymentResponse processPayment(@RequestBody PaymentRequest paymentRequest) {
	        PaymentResponse response = new PaymentResponse();
	        response.setStatus("SUCCESS");
	        response.setTransactionId(UUID.randomUUID().toString());
	        response.setAmount(paymentRequest.getAmount());
	        return paymentRepository.save(response);
	    }

}
