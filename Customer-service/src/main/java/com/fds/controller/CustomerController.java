package com.fds.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fds.dto.MenuItemDTO;
import com.fds.feign.MenuServiceClient;
import com.fds.model.Customer;
import com.fds.service.CustomerServiceImpl;

@RestController
@RequestMapping("/customers")//Base path for all customer-related endpoints
public class CustomerController {

	@Autowired
	private CustomerServiceImpl service;
	
	@Autowired
	private MenuServiceClient menuServiceClient;
	
	//Retrieves all menu items from the Menu Service  @return List of MenuItemDTO objects
	@GetMapping("/menu")
	public List<MenuItemDTO> getAllMenu() {
		return menuServiceClient.getAllMenu();
	}
	
	//Registers a new customer
	@PostMapping("/register")
	public ResponseEntity<Customer> register(@RequestBody Customer customer) {
		return ResponseEntity.ok(service.register(customer));
	}

	//Logs in a customer using email and password
	@PostMapping("/login")
	public ResponseEntity<Customer> login(@RequestBody Customer loginRequest) {
		return service.login(loginRequest.getEmail(), loginRequest.getPassword()).map(ResponseEntity::ok)
				.orElse(ResponseEntity.status(401).build());
	}
	
	//Retrieves a customer's profile by ID
	@GetMapping("/getcustomer/{id}")
	public Customer getProfile(@PathVariable Long id) {
		return service.getProfile(id).orElse(null);
	}
	
	//Updates customer profile information
	@PutMapping("/{id}")
	public Customer updateProfile(@PathVariable Long id, @RequestBody Customer customer) {
		return service.updateProfile(id, customer);
	}
	
	//Delete Customer Profile by using ID	
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		return service.deleteUser(id);
	}

}
