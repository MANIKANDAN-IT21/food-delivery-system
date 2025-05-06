package com.fds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fds.model.PaymentResponse;

public interface PaymentRepository extends JpaRepository<PaymentResponse, Long> {

}
