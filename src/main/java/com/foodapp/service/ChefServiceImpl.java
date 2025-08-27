package com.foodapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.entity.Order;
import com.foodapp.entity.User;
import com.foodapp.repository.OrderRepository;

@Service
public class ChefServiceImpl implements ChefService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Order> getOrdersForChef(User chef) {
		return orderRepository.findByAssignedChefId(chef.getId());
	}

	@Override
	public List<Order> getPendingOrders() {
		return orderRepository.findByStatus("PENDING");
	}

	@Override
	public List<Order> getUnassignedOrders() {
		return orderRepository.findByStatusAndAssignedChefIsNull("PENDING");
	}

	@Override
	public void updateOrderStatus(Long orderId, String statusStr) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

		Order.OrderStatus status;
		try {
			status = Order.OrderStatus.valueOf(statusStr.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Invalid status: " + statusStr);
		}
		order.setStatus(status);
		orderRepository.save(order);
	}

	@Override
	public void claimOrder(Long orderId, User chef) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

		if (order.getStatus() != Order.OrderStatus.PENDING) {
			throw new RuntimeException("Order cannot be claimed because it is not in PENDING status.");
		}

		order.setAssignedChef(chef);
		order.setStatus(Order.OrderStatus.IN_PROGRESS);

		orderRepository.save(order);
	}

	@Override
	public Order getOrderById(Long id) {
		return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
	}

}
