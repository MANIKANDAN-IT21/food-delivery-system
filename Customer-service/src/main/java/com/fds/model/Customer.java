package com.fds.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_info")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	
	//validations

	@NotBlank(message = "Name cannot be blank")
	private String name;
	
	@Email(message = "Invalid email format")
	@NotBlank(message = "Email cannot be blank")
	private String email;
	
	@NotBlank(message = "Password cannot be blank")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;
	
	@NotBlank(message = "Phone number cannot be blank")
	@Size(min = 10, message = "Enter the correct number")//@Pattern(regexp = "^[0-9]{10}$", message = "")
	private String phone;
	
	@NotBlank(message = "Address cannot be blank")
	private String address;

}
