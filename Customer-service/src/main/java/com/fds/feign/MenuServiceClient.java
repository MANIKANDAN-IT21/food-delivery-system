package com.fds.feign;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.fds.dto.MenuItemDTO;

@FeignClient(name = "menu-service", url = "http://localhost:2222")
public interface MenuServiceClient {
	
	@GetMapping("menu/getall")
	public List<MenuItemDTO> getAllMenu();
	

}
