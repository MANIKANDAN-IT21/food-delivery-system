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
		return service.addMenuItem(item);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MenuItem> updateItem(@PathVariable Long id, @RequestBody MenuItem item) {
		return ResponseEntity.ok(service.updateMenuItem(id, item));
	}
	
	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<List<MenuItem>> getMenuByRestaurant(@PathVariable Long restaurantId) {
		return ResponseEntity.ok(service.getMenuByRestaurant(restaurantId));
	}
	
	@GetMapping("/getall")
	public List<MenuItem> getAllMenu() {
		return service.getAllMenuItems();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable Long id) {
		service.deleteMenuItem(id);
		return ResponseEntity.ok("Deleted");
	}

}
