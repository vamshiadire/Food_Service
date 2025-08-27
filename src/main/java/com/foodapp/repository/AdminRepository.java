package com.foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapp.entity.Role;
import com.foodapp.entity.User;

@Repository
public interface AdminRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);

	// Find all users excluding those with the role "ADMIN"
	Iterable<User> findByRoleNot(Role ADMIN);

}
