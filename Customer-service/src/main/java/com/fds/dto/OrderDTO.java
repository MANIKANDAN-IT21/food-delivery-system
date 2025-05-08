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
public class OrderDTO {
	
	private Long orderId;
	private Long customerId;
	private Long restaurantId;
	private String status;
	private Double totalAmount;


}
