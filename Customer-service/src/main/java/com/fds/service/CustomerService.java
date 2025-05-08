package com.fds.service;

import java.util.Optional;

import com.fds.model.Customer;

public interface CustomerService {
	
	//Registers a new customer
	public abstract Customer register(Customer customer);
	
	//Logs in the customer using email and password
	public abstract  Optional<Customer> login(String email, String password);
	
	//Retrieves customer profile details by ID
	public abstract Optional<Customer> getProfile(Long id);

	//Updates customer profile information
	public abstract Customer updateProfile(Long id, Customer updated);
	
	//delete customer data
	public abstract String deleteUser(Long id);

}
