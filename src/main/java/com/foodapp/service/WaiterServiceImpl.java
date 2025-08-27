package com.foodapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.entity.CartItem;
import com.foodapp.entity.MenuItem;
import com.foodapp.entity.Order;
import com.foodapp.entity.Order.OrderStatus;
import com.foodapp.entity.OrderItem;
import com.foodapp.entity.RestaurantTable;
import com.foodapp.entity.User;
import com.foodapp.repository.MenuItemRepository;
import com.foodapp.repository.OrderRepository;
import com.foodapp.repository.RestaurantTableRepository;
import com.foodapp.repository.WaiterRepository;

@Service
public class WaiterServiceImpl implements WaiterService {

	@Autowired
	private WaiterRepository waiterRepository;

	@Autowired
	private RestaurantTableRepository restaurantTableRepository;

	@Autowired
	private MenuItemRepository menuItemRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<RestaurantTable> getAllTables() {
		return waiterRepository.findAll();
	}

	@Override
	public boolean isTableBooked(Long tableId) {
		Optional<RestaurantTable> table = waiterRepository.findById(tableId);
		return table.isPresent() && table.get().isStatus();
	}

	@Override
	public void bookTable(Long tableId, User user) {
		RestaurantTable table = restaurantTableRepository.findById(tableId).orElse(null);

		if (table != null && !table.isStatus()) {
			table.setStatus(true); // mark as booked
			table.setAssignedWaiter(user); // assign the logged-in user

			restaurantTableRepository.save(table);
		}
	}

	@Override
	public RestaurantTable getTableById(Long id) {
		return waiterRepository.findById(id).orElse(null);
	}

	@Override
	public RestaurantTable getTableByNumber(int tableNumber) {
		return waiterRepository.findByTableNumber(tableNumber).orElse(null);
	}

	@Override
	public void releaseTable(Long tableId, User waiter) {
		Optional<RestaurantTable> optionalTable = restaurantTableRepository.findById(tableId);
		if (optionalTable.isPresent()) {
			RestaurantTable table = optionalTable.get();

			if (table.getAssignedWaiter() != null && table.getAssignedWaiter().getId() == waiter.getId()) {

				table.setAssignedWaiter(null);
				table.setStatus(false);

				restaurantTableRepository.save(table);
			}
		} else {
			throw new IllegalArgumentException("Table not found with ID: " + tableId);
		}
	}

	@Override
	public RestaurantTable getTableByTableNumber(int tableNumber) {
		return restaurantTableRepository.findByTableNumber(tableNumber);
	}

	@Override
	public Optional<MenuItem> getItemById(Long id) {
		return menuItemRepository.findById(id);
	}

	@Override
	public void saveOrderFromCart(int tableId, List<CartItem> cartItems) {
		RestaurantTable table = restaurantTableRepository.findById((long) tableId)
				.orElseThrow(() -> new RuntimeException("Table not found"));

		Order order = new Order();
		order.setRestaurantTable(table); 
		order.setStatus(OrderStatus.PENDING);
		order.setAssignedChef(order.getAssignedChef());
		order.setLocalDateTime(LocalDateTime.now());

		List<OrderItem> orderItems = new ArrayList<>();

		for (CartItem cartItem : cartItems) {
			OrderItem orderItem = new OrderItem();
			orderItem.setItemName(cartItem.getItemName());
			orderItem.setPrice(cartItem.getPrice());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setOrder(order); // Set parent order
			orderItems.add(orderItem);
		}

		order.setOrderItems(orderItems);

		orderRepository.save(order); 
	}
	
	@Override
	public List<Order> getAllOrders() {
	    return orderRepository.findAll(); 
	}
	
	public List<Order> getCompletedOrders() {
	    return orderRepository.findByStatus(Order.OrderStatus.COMPLETED);
	}
	
	@Override
	public void claimOrder(Long orderId, User chef) {
	    Optional<Order> optionalOrder = orderRepository.findById(orderId);
	    if (optionalOrder.isPresent()) {
	        Order order = optionalOrder.get();
	        order.setAssignedChef(chef);  
	        orderRepository.save(order);  
	    } else {
	        throw new RuntimeException("Order not found with ID: " + orderId);
	    }
	}



}