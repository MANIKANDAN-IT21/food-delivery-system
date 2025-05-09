package com.fds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fds.dto.DeliveryDTO;
import com.fds.dto.MenuItemDTO;
import com.fds.dto.OrderDTO;
import com.fds.dto.OrderRequestDTO;
import com.fds.dto.PaymentRequest;
import com.fds.dto.PaymentResponse;
import com.fds.feign.DeliveryServiceClient;
import com.fds.feign.MenuServiceClient;
import com.fds.feign.OrderServiceClient;
import com.fds.feign.PaymentServiceClient;
import com.fds.model.Customer;
import com.fds.service.CustomerServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers") // Base path for all customer-related endpoints
public class CustomerController {

	@Autowired
	private CustomerServiceImpl service;

	@Autowired
	private MenuServiceClient menuServiceClient;

	@Autowired
	private DeliveryServiceClient deliveryServiceClient;

	@Autowired
	private OrderServiceClient orderServiceClient;

	@Autowired
	private PaymentServiceClient paymentServiceClient;

	// Retrieves all menu items from the Menu Service @return List of MenuItemDTO
	// objects
	@GetMapping("/menu")
	public List<MenuItemDTO> getAllMenu() {
		return menuServiceClient.getAllMenu();
	}

	@PostMapping("/process")
	public PaymentResponse processPayment(@RequestBody PaymentRequest request) {
		return paymentServiceClient.processPayment(request);
	}

	@PostMapping("/order")
	public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderRequestDTO request) {
		// Places a new order using a request DTO and returns a created status
		return new ResponseEntity<>(orderServiceClient.placeOrder(request), HttpStatus.CREATED);
	}
	
	   @GetMapping("/deliverystatus/{id}")
	    public DeliveryDTO get(@PathVariable Long id) {
	    	// Fetches details of a specific delivery using its ID
	        return deliveryServiceClient.getDelivery(id);
	    }

	// Registers a new customer
	@PostMapping("/register")
	public ResponseEntity<Customer> register(@RequestBody @Validated Customer customer) {
		return ResponseEntity.ok(service.register(customer));
	}

	// Logs in a customer using email and password
	@PostMapping("/login")
	public ResponseEntity<Customer> login(@RequestBody Customer loginRequest) {
		return service.login(loginRequest.getEmail(), loginRequest.getPassword()).map(ResponseEntity::ok)
				.orElse(ResponseEntity.status(401).build());
	}

	// Retrieves a customer's profile by ID
	@GetMapping("/getcustomer/{id}")
	public Customer getProfile(@PathVariable Long id) {
		return service.getProfile(id).orElse(null);
	}

	// Updates customer profile information
	@PutMapping("/{id}")
	public Customer updateProfile(@PathVariable Long id, @RequestBody Customer customer) {
		return service.updateProfile(id, customer);
	}

	// Delete Customer Profile by using ID
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		return service.deleteUser(id);
	}

}
