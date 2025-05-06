package com.fds.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fds.model.Delivery;
import com.fds.repository.DeliveryRepository;
@Service
public class DeliveryServiceImpl implements DeliveryService {
	@Autowired
	DeliveryRepository repository;
	
	@Override
    public List<Delivery> getDeliveries() {
        return repository.findAll(); // Fetch all deliveries
    }

    @Override
    public Delivery status(Delivery deliveryRequest) {
        deliveryRequest.setDeliveryId(UUID.randomUUID().toString()); // Generate unique ID
        deliveryRequest.setStatus("PENDING"); // Set status
        return repository.save(deliveryRequest); // Save to database
    }
}
