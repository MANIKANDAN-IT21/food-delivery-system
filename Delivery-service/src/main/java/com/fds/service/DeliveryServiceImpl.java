package com.fds.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fds.model.Agent;
import com.fds.model.Delivery;
import com.fds.repository.DeliveryRepository;
@Service
public class DeliveryServiceImpl implements DeliveryService {
	
	@Autowired
    private DeliveryRepository deliveryRepo;
	
	@Autowired
    private AgentService agentService;
	
    public List<Delivery> getDeliveries() {
        return deliveryRepo.findAll(); // Fetch all deliveries
    }
//
//    @Override
//    public Delivery status(Delivery deliveryRequest) {
//        deliveryRequest.setStatus("PENDING"); // Set status
//        return repository.save(deliveryRequest); // Save to database
//    }

	@Override
	public Delivery assignAgentToOrder(Long orderId) {
		 Agent agent = agentService.getAvailableAgent();
	        agentService.updateAgentStatus(agent.getAgentId(), "Busy");

	        Delivery delivery = new Delivery();
	        delivery.setOrderId(orderId);
	        delivery.setAgentId(agent.getAgentId());
	        delivery.setStatus("In Progress");

	        return deliveryRepo.save(delivery);
	}

	@Override
	public Delivery updateDeliveryStatus(Long deliveryId, String status) {
		 Delivery delivery = deliveryRepo.findById(deliveryId)
	                .orElseThrow(() -> new RuntimeException("Delivery not found"));

	        delivery.setStatus(status);
	        Delivery updated = deliveryRepo.save(delivery);

	        if ("Delivered".equalsIgnoreCase(status)) {
	            agentService.updateAgentStatus(delivery.getAgentId(), "Available");
	        }

	        return updated;
	}

	@Override
	public Delivery getDelivery(Long deliveryId) {
		return deliveryRepo.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
    }
}
