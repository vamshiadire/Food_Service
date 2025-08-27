package com.foodapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.entity.BillAmount;

public interface BillAmountRepository extends JpaRepository<BillAmount, Integer> {

	List<BillAmount> findByWaiterName(String waiterName);

}
