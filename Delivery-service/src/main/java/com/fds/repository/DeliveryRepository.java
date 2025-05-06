package com.fds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fds.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
