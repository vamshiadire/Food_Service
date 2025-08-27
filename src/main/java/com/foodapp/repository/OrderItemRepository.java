package com.foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodapp.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
