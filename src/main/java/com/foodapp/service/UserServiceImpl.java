package com.foodapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.entity.Role;
import com.foodapp.entity.User;
import com.foodapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AdminService adminService;

    @Override
    public User login(String email, String password) {
        return userRepo.findByEmailAndPassword(email, password);
    }

    @Override
    public boolean registerUser(User user, String token) {
        if (!adminService.validateToken(token) || userRepo.existsByEmail(user.getEmail())) {
            return false;
        }

        user.setRole(Enum.valueOf(Role.class, adminService.getRoleFromToken(token)));
        userRepo.save(user);
        adminService.markTokenAsUsed(token);
        return true;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

}
