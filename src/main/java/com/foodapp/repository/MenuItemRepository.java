package com.foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

}
