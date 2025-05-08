package com.fds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fds.model.MenuItem;
import com.fds.service.RestaurantService;

@RestController
@RequestMapping("/menu")
public class RestaurantController {
	
	@Autowired
	RestaurantService service;
	
	@PostMapping
	public MenuItem addItem(@RequestBody MenuItem item) {
		// Adds a new menu item
		return service.addMenuItem(item);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MenuItem> updateItem(@PathVariable Long id, @RequestBody MenuItem item) {
		// Updates an existing menu item
		return ResponseEntity.ok(service.updateMenuItem(id, item));
	}
	
	@GetMapping("/restaurant/{restaurantId}")
	public MenuItem getMenuByRestaurant(@PathVariable Long restaurantId) {
		// Retrieves a menu item by restaurant ID
		return service.getMenuByRestaurant(restaurantId);
	}
	
	@GetMapping("/getall")
	public List<MenuItem> getAllMenu() {
		return service.getAllMenuItems();// Fetches all menu items
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable Long id) {
		service.deleteMenuItem(id);
		return ResponseEntity.ok("Deleted");// Deletes a menu item by ID and returns a success message
	}

}
