package com.fds.service;

import java.util.List;

import com.fds.model.MenuItem;

public interface RestaurantService {
	
	//restaurant owner adding the items
	MenuItem addMenuItem(MenuItem item);

	//restaurant owner Updating the items
	MenuItem updateMenuItem(Long itemId, MenuItem updatedItem);
	
	//restaurant deleting adding the items
	void deleteMenuItem(Long itemId);
	
//	List<MenuItem> getMenuById(Long itemId);

	//restaurant owner get the menu by Id
	MenuItem getMenuByRestaurant(Long restaurantId);

	//restaurant owner see the  List of items
	List<MenuItem> getAllMenuItems();

}
