package com.fds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fds.model.MenuItem;

public interface RestautrantRepository extends JpaRepository<MenuItem, Long>{
	
	MenuItem findByRestaurantId(Long restaurantId);//
	
//	List<MenuItem> findByRestaurantId(Long restaurantId);
	
	/*
	 * MenuItem per restaurant, but in reality, multiple menu items might exist for
	 * a single restaurant. This can lead to a NonUniqueResultException
	 */

}
