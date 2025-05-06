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
		return repository.save(customer);
	}

	@Override
	public Optional<Customer> login(String email, String password) {
		return repository.findByEmail(email).filter(c -> c.getPassword().equals(password));
	}

	@Override
	public Optional<Customer> getProfile(Long id) {
		return repository.findById(id);
	}

	@Override
	public Customer updateProfile(Long id, Customer updated) {
		Customer c = repository.findById(id).orElseThrow();
		c.setName(updated.getName());
		c.setAddress(updated.getAddress());
		c.setPhone(updated.getPhone());
		return repository.save(c);
	}

}
