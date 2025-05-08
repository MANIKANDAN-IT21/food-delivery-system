package com.fds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fds.dto.PaymentRequest;
import com.fds.dto.PaymentResponse;
import com.fds.feign.DeliveryClient;
import com.fds.model.Payment;
import com.fds.repository.PaymentRepository;

@Service
public class PaymentServiceImpl  implements PaymentService {
	
	 @Autowired
	    private PaymentRepository paymentRepo;

	    @Autowired
	    private DeliveryClient deliveryClient;

	    @Override
	    public PaymentResponse processPayment(PaymentRequest request) {
	        Payment payment = new Payment();
	        payment.setOrderId(request.getOrderId());
	        payment.setPaymentMethod(request.getPaymentMethod());
	        payment.setAmount(request.getAmount());

	        // Simulate payment logic (in real case, integrate payment gateway)
	        boolean isPaymentSuccess = true; // mock result

	        if (isPaymentSuccess) {
	            payment.setStatus("Successful");
	            paymentRepo.save(payment);

	            // Notify delivery-service to assign delivery
	            deliveryClient.assignAgentToOrder(request.getOrderId());

	            return new PaymentResponse("Payment Successful. Delivery Assigned.", "Successful");
	        } else {
	            payment.setStatus("Failed");
	            paymentRepo.save(payment);
	            return new PaymentResponse("Payment Failed", "Failed");
	        }
	    }

}
