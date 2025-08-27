package com.foodapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foodapp.entity.Order;
import com.foodapp.entity.User;


@Service
public interface ChefService {

	List<Order> getPendingOrders();

	List<Order> getOrdersForChef(User chef);

	List<Order> getUnassignedOrders();

	void claimOrder(Long orderId, User chef);

	void updateOrderStatus(Long orderId, String status);
	
	Order getOrderById(Long id);
}
