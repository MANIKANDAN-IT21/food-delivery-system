package com.fds.service;

import java.util.List;

import com.fds.model.MenuItem;

public interface RestaurantService {
	MenuItem addMenuItem(MenuItem item);

	MenuItem updateMenuItem(Long itemId, MenuItem updatedItem);

	void deleteMenuItem(Long itemId);

	List<MenuItem> getMenuByRestaurant(Long restaurantId);

	List<MenuItem> getAllMenuItems();

}
