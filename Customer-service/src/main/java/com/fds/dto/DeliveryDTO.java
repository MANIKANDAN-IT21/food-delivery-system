package com.fds.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDTO {

	private Long deliveryId;
	private Long orderId;
	private Long agentId;
	private String status;

}
