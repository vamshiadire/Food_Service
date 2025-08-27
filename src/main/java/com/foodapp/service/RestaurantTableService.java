package com.foodapp.service;

import java.util.List;
import java.util.Optional;

import com.foodapp.entity.RestaurantTable;

public interface RestaurantTableService {
	List<RestaurantTable> getAllTables();

	void addTable(int tableNumber);
	
	Optional<RestaurantTable> getTableById(Long tableId);
	
	RestaurantTable continueOrder(Long tableId, String username); 
}
