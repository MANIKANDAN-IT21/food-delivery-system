package com.fds.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;
    private Long orderId;
    private Long agentId;
    private String status;

}
