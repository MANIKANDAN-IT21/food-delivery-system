package com.fds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fds.model.MenuItem;
import com.fds.repository.RestautrantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	RestautrantRepository repository;

	@Override
	public MenuItem addMenuItem(MenuItem item) {
		// Adds a new menu item to the repository
		return repository.save(item);
	}

	@Override
	public MenuItem updateMenuItem(Long itemId, MenuItem updatedItem) {
		// Finds an existing menu item by ID, updates its properties, and saves the changes
		MenuItem item = repository.findById(itemId).orElseThrow();
        item.setName(updatedItem.getName());
        item.setDescription(updatedItem.getDescription());
        item.setPrice(updatedItem.getPrice());
        return repository.save(item);
	}

	@Override
	public void deleteMenuItem(Long itemId) {
		repository.deleteById(itemId);// Deletes a menu item by its ID
		
	}

	@Override
	public MenuItem getMenuByRestaurant(Long restaurantId) {
		MenuItem menuitem=repository.findByRestaurantId(restaurantId); // Finds a menu item by restaurant ID
		return menuitem;
	}

	@Override
	public List<MenuItem> getAllMenuItems() {
		return repository.findAll();// Retrieves all menu items from the repository
	}


}
