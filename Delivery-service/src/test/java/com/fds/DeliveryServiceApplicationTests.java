package com.fds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.fds.model.Agent;
import com.fds.model.Delivery;
import com.fds.repository.DeliveryRepository;
import com.fds.service.AgentService;
import com.fds.service.DeliveryServiceImpl;

@SpringBootTest
class DeliveryServiceApplicationTests {
	
	 @InjectMocks
	    private DeliveryServiceImpl deliveryService;

	    @Mock
	    private DeliveryRepository deliveryRepo;

	    @Mock
	    private AgentService agentService;
	    
	    @Test
	    public void testAssignAgentToOrder_Success() {
	        // Create mock agent
	        Agent mockAgent = new Agent(1L, "John Doe", "9876543210", "Available");
	        
	        // Mock agent retrieval and status update
	        Mockito.when(agentService.getAvailableAgent()).thenReturn(mockAgent);
	        Mockito.doNothing().when(agentService).updateAgentStatus(Mockito.anyLong(), Mockito.anyString());

	        // Mock saving delivery entry
	        Delivery mockDelivery = new Delivery(1L, 100L, 1L, "In Progress");
	        Mockito.when(deliveryRepo.save(Mockito.any(Delivery.class))).thenReturn(mockDelivery);

	        // Run the test
	        Delivery result = deliveryService.assignAgentToOrder(100L);

	        // Assertions
	        assertNotNull(result);
	        assertEquals(100L, result.getOrderId());
	        assertEquals(1L, result.getAgentId());
	        assertEquals("In Progress", result.getStatus());

	        // Verify interactions
	        Mockito.verify(agentService, Mockito.times(1)).getAvailableAgent();
	        Mockito.verify(agentService, Mockito.times(1)).updateAgentStatus(1L, "Busy");
	        Mockito.verify(deliveryRepo, Mockito.times(1)).save(Mockito.any(Delivery.class));
	    }

	    // Test case for updating delivery status
	    @Test
	    public void testUpdateDeliveryStatus() {
	        // Mock existing delivery entry
	        Delivery mockDelivery = new Delivery(1L, 100L, 1L, "In Progress");
	        Mockito.when(deliveryRepo.findById(1L)).thenReturn(Optional.of(mockDelivery));

	        // Mock save action
	        Mockito.when(deliveryRepo.save(Mockito.any(Delivery.class))).thenAnswer(invocation -> invocation.getArgument(0));

	        // Run test
	        Delivery updatedDelivery = deliveryService.updateDeliveryStatus(1L, "Delivered");

	        // Assertions
	        assertNotNull(updatedDelivery);
	        assertEquals("Delivered", updatedDelivery.getStatus());

	        // Verify agent status is updated to available
	        Mockito.verify(agentService, Mockito.times(1)).updateAgentStatus(mockDelivery.getAgentId(), "Available");
	    }

	    // Test case for retrieving a delivery by ID
	    @Test
	    public void testGetDeliveryById_Success() {
	        // Mock delivery retrieval
	        Delivery mockDelivery = new Delivery(1L, 100L, 2L, "Delivered");
	        Mockito.when(deliveryRepo.findById(1L)).thenReturn(Optional.of(mockDelivery));

	        // Run the test
	        Delivery result = deliveryService.getDelivery(1L);

	        // Assertions
	        assertNotNull(result);
	        assertEquals(1L, result.getDeliveryId());
	        assertEquals(100L, result.getOrderId());
	        assertEquals(2L, result.getAgentId());
	        assertEquals("Delivered", result.getStatus());

	        // Verify interactions
	        Mockito.verify(deliveryRepo, Mockito.times(1)).findById(1L);
	    }

	    // Test case for handling invalid delivery ID
	    @Test
	    public void testGetDeliveryById_NotFound() {
	        // Simulate that no delivery is found
	        Mockito.when(deliveryRepo.findById(999L)).thenReturn(Optional.empty());

	        // Assert that a RuntimeException is thrown when getDelivery is called
	        Assertions.assertThrows(RuntimeException.class, () -> {
	            deliveryService.getDelivery(999L);
	        });
	    }

	}

