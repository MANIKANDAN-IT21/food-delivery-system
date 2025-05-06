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
		return repository.save(item);
	}

	@Override
	public MenuItem updateMenuItem(Long itemId, MenuItem updatedItem) {
		MenuItem item = repository.findById(itemId).orElseThrow();
        item.setName(updatedItem.getName());
        item.setDescription(updatedItem.getDescription());
        item.setPrice(updatedItem.getPrice());
        return repository.save(item);
	}

	@Override
	public void deleteMenuItem(Long itemId) {
		repository.deleteById(itemId);
		
	}

	@Override
	public List<MenuItem> getMenuByRestaurant(Long restaurantId) {
		return repository.findByRestaurantId(restaurantId);
	}

	@Override
	public List<MenuItem> getAllMenuItems() {
		return repository.findAll();
	}

}
