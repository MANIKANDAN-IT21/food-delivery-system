package com.fds.service;

import java.util.Optional;

import com.fds.model.Customer;

public interface CustomerService {
	
	Customer register(Customer customer);

	Optional<Customer> login(String email, String password);

	Optional<Customer> getProfile(Long id);

	Customer updateProfile(Long id, Customer updated);
	
	//delete,validation,exception,erukaserver,gateway,junit,mockito

}
