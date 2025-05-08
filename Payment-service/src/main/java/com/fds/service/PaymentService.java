package com.fds.service;

import com.fds.dto.PaymentRequest;
import com.fds.dto.PaymentResponse;

public interface PaymentService {
	
	 PaymentResponse processPayment(PaymentRequest request);

}
