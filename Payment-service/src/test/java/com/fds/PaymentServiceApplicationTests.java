package com.fds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.fds.dto.PaymentRequest;
import com.fds.dto.PaymentResponse;
import com.fds.feign.DeliveryClient;
import com.fds.model.Payment;
import com.fds.repository.PaymentRepository;
import com.fds.service.PaymentServiceImpl;

@SpringBootTest
class PaymentServiceApplicationTests {
	
    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepo;

    @Mock
    private DeliveryClient deliveryClient;

    @Test
    public void testProcessPayment_Success() {
        // Create mock payment request
        PaymentRequest request = new PaymentRequest(1L, "Credit Card", 100.0);

        // Mock repository save behavior
        Mockito.when(paymentRepo.save(Mockito.any(Payment.class))).thenAnswer(invocation -> {
            Payment payment = invocation.getArgument(0);
            payment.setPaymentId(1L); // Simulate DB save
            return payment;
        });

        // Run the test
        PaymentResponse response = paymentService.processPayment(request);

        // Assertions
        assertNotNull(response);
        assertEquals("Successful", response.getStatus());
        assertEquals("Payment Successful. Delivery Assigned.", response.getMessage());

        // Verify delivery client was called
        Mockito.verify(deliveryClient, Mockito.times(1)).assignAgentToOrder(1L);
    }
    
    @Test
    public void testProcessPayment_Failure() {
        // Create mock payment request
        PaymentRequest request = new PaymentRequest(2L, "Debit Card", 50.0);

        // Simulate failed payment scenario
        Mockito.doAnswer(invocation -> invocation.getArgument(0))
               .when(paymentRepo).save(Mockito.any(Payment.class));

        // Simulate failure (modify `isPaymentSuccess` logic if needed)
        PaymentResponse response = paymentService.processPayment(request);

        // Assertions
        assertNotNull(response);
        assertEquals("Failed", response.getStatus());
        assertEquals("Payment Failed", response.getMessage());

        // Verify delivery client was **NOT** called
        Mockito.verify(deliveryClient, Mockito.never()).assignAgentToOrder(2L);
    }
}

