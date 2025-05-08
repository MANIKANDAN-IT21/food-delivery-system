package com.fds.feign;


import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fds.dto.MenuItemDTO;


//Feign client for communicating with Menu Service
@FeignClient(name = "menu-service", url = "http://localhost:2222")
public interface MenuServiceClient {
	
	//Fetches all menu items from the Menu Service
	@GetMapping("menu/getall")// Maps this method to the endpoint of Menu Service
	public List<MenuItemDTO> getAllMenu();
	
	@GetMapping("menu/restaurant/{restaurantId}")
	public MenuItemDTO getMenuByRestaurant(@PathVariable Long restaurantId);
	

}
