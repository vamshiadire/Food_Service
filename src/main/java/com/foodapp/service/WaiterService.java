package com.foodapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.foodapp.entity.CartItem;
import com.foodapp.entity.MenuItem;
import com.foodapp.entity.Order;
import com.foodapp.entity.RestaurantTable;
import com.foodapp.entity.User;

@Service
public interface WaiterService {

	List<RestaurantTable> getAllTables();

	boolean isTableBooked(Long tableId);

	void bookTable(Long tableId, User user);

	RestaurantTable getTableByNumber(int tableNumber);

	RestaurantTable getTableById(Long id);

	RestaurantTable getTableByTableNumber(int tableNumber);

	void releaseTable(Long tableId, User waiter);

	Optional<MenuItem> getItemById(Long id);

	void saveOrderFromCart(int tableId, List<CartItem> cartItems);
	
	List<Order> getAllOrders();
	
	List<Order> getCompletedOrders();

	void claimOrder(Long orderId, User chef);


}
