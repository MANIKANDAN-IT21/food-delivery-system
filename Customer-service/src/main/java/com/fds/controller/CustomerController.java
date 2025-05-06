package com.fds.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerServiceImpl service;
	
	@Autowired
	private MenuServiceClient menuServiceClient;
	
	@GetMapping("/menu")
	public List<MenuItemDTO> getAllMenu() {
		return menuServiceClient.getAllMenu();
	}

	@PostMapping("/register")
	public ResponseEntity<Customer> register(@RequestBody Customer customer) {
		return ResponseEntity.ok(service.register(customer));
	}

	@PostMapping("/login")
	public ResponseEntity<Customer> login(@RequestBody Customer loginRequest) {
		return service.login(loginRequest.getEmail(), loginRequest.getPassword()).map(ResponseEntity::ok)
				.orElse(ResponseEntity.status(401).build());
	}

	@GetMapping("/getcustomer/{id}")
	public Customer getProfile(@PathVariable Long id) {
		return service.getProfile(id).orElse(null);
	}

	@PutMapping("/{id}")
	public Customer updateProfile(@PathVariable Long id, @RequestBody Customer customer) {
		return service.updateProfile(id, customer);
	}

}
