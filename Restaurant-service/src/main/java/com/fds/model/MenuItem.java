package com.fds.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu_info")     
public class MenuItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;
	//validation
	
	@NotBlank(message = "Name cannot be blank")
	private String name;
	
	@NotBlank(message = "Name cannot be blank")
	private String description;
	
	@NotNull(message = "Price cannot be null")
	@Positive(message = "Price must be positive")
	private Double price;
	
	private Long restaurantId;//
	
	
}
