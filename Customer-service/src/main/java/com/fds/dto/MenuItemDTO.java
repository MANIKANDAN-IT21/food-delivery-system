package com.fds.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemDTO {
	
	/**
	 * DTO (Data Transfer Object) for Menu Items
	 * Used to transfer menu item data between services.
	 */
	
	private Long itemId;
    private String name;
    private String description;
    private Double price;
}


