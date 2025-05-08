package com.fds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fds.dto.MenuItemDTO;
import com.fds.dto.OrderRequestDTO;
import com.fds.feign.MenuClientService;
import com.fds.model.Order;
import com.fds.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
    private OrderRepository repository;
	
    @Autowired
    private MenuClientService menuClient;

	public Order placeOrder(Order order) {
		order.setStatus("Pending");
        return repository.save(order);
	}

	public Order getOrderById(Long id) {
		 return repository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
	}
	
	public Order updateStatus(Long id, String status) {
		Order order = getOrderById(id);
        order.setStatus(status);
        return repository.save(order);
	}

	public List<Order> allOrder() { 
        return repository.findAll();
    }

	@Override
	public Order placeOrder(OrderRequestDTO request) {
		 MenuItemDTO item = menuClient.getMenuByRestaurant(request.getItemId());

	        Order order = new Order();
	        order.setCustomerId(request.getCustomerId());
	        order.setRestaurantId(item.getRestaurantId());
	        order.setTotalAmount(item.getPrice());
	        order.setStatus("Your order is pending. Please complete the payment to proceed.");

	        return repository.save(order);
	    }

}
