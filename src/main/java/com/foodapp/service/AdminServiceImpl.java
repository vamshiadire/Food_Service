package com.foodapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import com.foodapp.entity.MenuItem;
import com.foodapp.entity.Role;
import com.foodapp.entity.User;
import com.foodapp.repository.MenuItemRepository;
import com.foodapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private MenuItemRepository menuItemRepository;

	private static final Map<String, Boolean> tokenStore = new HashMap<>();
	private static final Map<String, String> tokenRoleMap = new HashMap<>();
	private static final Map<String, Long> tokenExpiryMap = new HashMap<>();
	private static final long EXPIRY_DURATION_MS = 5 * 60 * 1000;

	@Override
	public String generateToken(String role) {
		String token = UUID.randomUUID().toString();
		tokenStore.put(token, false);
		tokenRoleMap.put(token, role);
		tokenExpiryMap.put(token, System.currentTimeMillis() + EXPIRY_DURATION_MS);
		return token;
	}

	@Override
	public boolean validateToken(String token) {
		Long expiry = tokenExpiryMap.get(token);
		return tokenStore.containsKey(token) && Boolean.FALSE.equals(tokenStore.get(token)) && expiry != null
				&& System.currentTimeMillis() < expiry;
	}

	@Override
	public String getRoleFromToken(String token) {
		return tokenRoleMap.get(token);
	}

	@Override
	public void markTokenAsUsed(String token) {
		tokenStore.put(token, true);
	}

	@Override
	public boolean promoteUserToAdmin(Long userId) {
		return userRepo.findById(userId).map(user -> {
			user.setRole(Role.ADMIN);
			userRepo.save(user);
			return true;
		}).orElse(false);
	}

	@Override
	public Iterable<User> getAllUsersExcludingAdmins() {
		return userRepo.findByRoleNot(Role.ADMIN);
	}

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public MenuItem addItem(MenuItem menuItem) {
		return menuItemRepository.save(menuItem);
	}

	@Override
	public List<MenuItem> findAllMenu() {
		return menuItemRepository.findAll();
	}

	

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<User> findById(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}
