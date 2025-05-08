package com.fds.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fds.dto.MenuItemDTO;

@FeignClient(name = "restautrant-service", url = "http://localhost:2222")
public interface MenuClientService {

	@GetMapping("menu/restaurant/{restaurantId}")
	// Retrieves the menu details for a given restaurant ID
	MenuItemDTO getMenuByRestaurant(@PathVariable Long restaurantId);
}
