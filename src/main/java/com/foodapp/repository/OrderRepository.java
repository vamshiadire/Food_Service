package com.foodapp.repository;

import com.foodapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatus(String status);

    List<Order> findByAssignedChefId(Long chefId);

    List<Order> findByRestaurantTable_Id(Long tableId); 

    List<Order> findByStatusAndAssignedChefIsNull(String status);

    List<Order> findByStatusAndAssignedChefId(String status, Long chefId);
    
    List<Order> findByStatus(Order.OrderStatus status);

}
