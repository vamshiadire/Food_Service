package com.foodapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.entity.RestaurantTable;

public interface WaiterRepository extends JpaRepository<RestaurantTable, Long> {
	Optional<RestaurantTable> findByTableNumber(int tableNumber);

}
