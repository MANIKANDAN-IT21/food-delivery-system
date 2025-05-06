package com.fds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fds.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

}
