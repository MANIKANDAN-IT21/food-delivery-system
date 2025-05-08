package com.fds.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
	
	private Long paymentId;
	private Long orderId;
	private String paymentMethod;
	private Double amount;
	private String status; 

}
