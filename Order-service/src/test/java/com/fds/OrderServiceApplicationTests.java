package com.fds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.fds.dto.MenuItemDTO;
import com.fds.dto.OrderRequestDTO;
import com.fds.feign.MenuClientService;
import com.fds.model.Order;
import com.fds.repository.OrderRepository;
import com.fds.service.OrderServiceImpl;

@SpringBootTest
class OrderServiceApplicationTests {
	
	
	@InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MenuClientService menuClient;

    private Order order;
    private OrderRequestDTO orderRequest;
    private MenuItemDTO menuItem;

    @BeforeEach
    public void setUp() {
        order = new Order(1L, 101L, 202L, "Pending", 599.99);
        menuItem = new MenuItemDTO(202L, "Pizza", "Delicious cheese pizza", 599.99, 202L);
        orderRequest = new OrderRequestDTO(101L, 202L);
    }


    @Test
    public void testPlaceOrderWithDTO() {
        Mockito.when(menuClient.getMenuByRestaurant(orderRequest.getItemId())).thenReturn(menuItem);
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);

        Order placedOrder = orderService.placeOrder(orderRequest);

        assertNotNull(placedOrder);
        assertEquals("Pending", placedOrder.getStatus());
        assertEquals(599.99, placedOrder.getTotalAmount(), 0.01);
    }

    @Test
    public void testPlaceOrderDirectly() {
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);

        Order placedOrder = orderService.placeOrder(order);

        assertNotNull(placedOrder);
        assertEquals("Pending", placedOrder.getStatus());
    }

    @Test
    public void testGetOrderById_Success() {
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order fetchedOrder = orderService.getOrderById(1L);

        assertNotNull(fetchedOrder);
        assertEquals(1L, fetchedOrder.getOrderId().longValue());
    }

    @Test
    public void testGetOrderById_NotFound() {
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> {
            orderService.getOrderById(1L);
        });
    }

    @Test
    public void testUpdateOrderStatus() {
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);

        Order updatedOrder = orderService.updateStatus(1L, "Completed");

        assertNotNull(updatedOrder);
        assertEquals("Completed", updatedOrder.getStatus());
    }

    @Test
    public void testGetAllOrders() {
        List<Order> orders = Arrays.asList(order, new Order(2L, 103L, 204L, "Pending", 299.99));

        Mockito.when(orderRepository.findAll()).thenReturn(orders);

        List<Order> fetchedOrders = orderService.allOrder();

        assertEquals(2, fetchedOrders.size());
    }

}
