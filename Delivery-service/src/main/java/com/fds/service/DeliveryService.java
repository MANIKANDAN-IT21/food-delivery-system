package com.fds.service;

import java.util.List;

import com.fds.model.Delivery;

public interface DeliveryService {
	
	List<Delivery> getDeliveries(); 
    Delivery status(Delivery deliveryRequest);

}
