package com.fds.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fds.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	Optional<Customer> findByEmail(String email);//Finds a customer by email
	
	Optional<Customer> findByEmailAndPassword(String email, String password);

}
