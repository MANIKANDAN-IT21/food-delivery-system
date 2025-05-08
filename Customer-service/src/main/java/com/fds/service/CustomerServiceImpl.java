package com.fds.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fds.model.Customer;
import com.fds.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Override
	public Customer register(Customer customer) {
		return repository.save(customer); // Saves the new customer in the database
	}

	@Override
	public Optional<Customer> login(String email, String password) {
		return repository.findByEmail(email)// Searches for the customer by email
				.filter(c -> c.getPassword().equals(password));// Validates password
	}

	@Override
	public Optional<Customer> getProfile(Long id) {
		return repository.findById(id);// Fetches customer details from the database
	}

	@Override
	public Customer updateProfile(Long id, Customer updated) {
		Customer c = repository.findById(id).orElseThrow();// Retrieves customer or throws an exception if not found
		c.setName(updated.getName());
		c.setAddress(updated.getAddress());
		c.setPhone(updated.getPhone());
		return repository.save(c);// Saves the updated customer information
	}

	@Override
	public String deleteUser(Long id) {
		repository.deleteById(id);
		return "deleted";//delete the customer by ID
	}

}
