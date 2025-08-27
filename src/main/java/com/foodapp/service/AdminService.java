package com.foodapp.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.foodapp.entity.MenuItem;
import com.foodapp.entity.User;

@Service
public interface AdminService {

	String generateToken(String role);

	boolean validateToken(String token);

	String getRoleFromToken(String token);

	void markTokenAsUsed(String token);

	boolean promoteUserToAdmin(Long userId);

	Iterable<User> getAllUsersExcludingAdmins();

	List<User> findAll();

	MenuItem addItem(MenuItem menuItem);

	List<MenuItem> findAllMenu();

	void deleteById(int id);

	Optional<User> findById(int id);
}
