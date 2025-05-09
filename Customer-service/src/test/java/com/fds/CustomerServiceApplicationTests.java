package com.fds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.fds.model.Customer;
import com.fds.repository.CustomerRepository;
import com.fds.service.CustomerServiceImpl;

@SpringBootTest
class CustomerServiceApplicationTests {
	
	@InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;
	
	
    @BeforeEach
    public void setUp() {
        customer = new Customer(1L, "John Doe", "john@example.com", "securePass123", "1234567890", "123 Street, City");
    }
    
    @Test
    public void testRegisterCustomer() {
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        
        Customer savedCustomer = customerService.register(customer);
        
        assertNotNull(savedCustomer);
        assertEquals("John Doe", savedCustomer.getName());
    }

    @Test
    public void testLoginCustomer_Success() {
        Mockito.when(customerRepository.findByEmail("john@example.com")).thenReturn(Optional.of(customer));
        
        Optional<Customer> loggedInCustomer = customerService.login("john@example.com", "securePass123");
        
        assertTrue(loggedInCustomer.isPresent());
        assertEquals("John Doe", loggedInCustomer.get().getName());
    }

    @Test
    public void testLoginCustomer_Failure() {
        Mockito.when(customerRepository.findByEmail("john@example.com")).thenReturn(Optional.of(customer));
        
        Optional<Customer> loggedInCustomer = customerService.login("john@example.com", "wrongPass");
        
        assertFalse(loggedInCustomer.isPresent());
    }

    @Test
    public void testGetProfile() {
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        
        Optional<Customer> profile = customerService.getProfile(1L);
        
        assertTrue(profile.isPresent());
        assertEquals("John Doe", profile.get().getName());
    }

    @Test
    public void testUpdateProfile() {
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        
        Customer updatedCustomer = new Customer(1L, "Jane Doe", "john@example.com", "securePass123", "0987654321", "456 Avenue, City");
        Customer result = customerService.updateProfile(1L, updatedCustomer);
        
        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        assertEquals("0987654321", result.getPhone());
        assertEquals("456 Avenue, City", result.getAddress());
    }

    @Test
    public void testDeleteUser() {
        Mockito.doNothing().when(customerRepository).deleteById(1L);
        
        String result = customerService.deleteUser(1L);
        
        assertEquals("deleted", result);
    }

}
