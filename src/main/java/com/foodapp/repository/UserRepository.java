package com.foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapp.entity.Role;
import com.foodapp.entity.User;

@Repository

public interface UserRepository extends JpaRepository<User,Long> {

	User findByEmailAndPassword(String email, String password);

	boolean existsByEmail(String email);

	User findByEmail(String email);

	Iterable<User> findByRoleNot(Role ADMIN);
}