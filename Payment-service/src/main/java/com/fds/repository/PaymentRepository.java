package com.fds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fds.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
