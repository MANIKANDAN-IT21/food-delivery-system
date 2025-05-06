package com.fds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fds.model.MenuItem;

public interface RestautrantRepository extends JpaRepository<MenuItem, Long>{
	
	List<MenuItem> findByRestaurantId(Long restaurantId);

}
