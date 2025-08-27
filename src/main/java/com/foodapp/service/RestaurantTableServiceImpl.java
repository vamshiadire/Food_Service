package com.foodapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.entity.RestaurantTable;
import com.foodapp.repository.RestaurantTableRepository;

@Service
public class RestaurantTableServiceImpl implements RestaurantTableService {

	@Autowired
	private RestaurantTableRepository restaurantTableRepository;

	@Override
	public List<RestaurantTable> getAllTables() {
		return restaurantTableRepository.findAll();
	}

	@Override
	public void addTable(int tableNumber) {
		if (restaurantTableRepository.existsByTableNumber(tableNumber)) {
			throw new IllegalArgumentException("Table number already exists!");
		}

		RestaurantTable table = new RestaurantTable();
		table.setTableNumber(tableNumber);
		table.setStatus(false); // default status: available
		restaurantTableRepository.save(table);
	}

	@Override
	public Optional<RestaurantTable> getTableById(Long tableId) {
		return restaurantTableRepository.findById(tableId);

	}

	@Override
	public RestaurantTable continueOrder(Long tableId, String username) {
		return getTableById(tableId).filter(table -> table.isStatus())
				.filter(table -> table.getAssignedWaiter() != null)
				.filter(table -> username.equals(table.getAssignedWaiter().getUsername()))
				.orElseThrow(() -> new RuntimeException("Unauthorized or Table not found"));
	}

}
