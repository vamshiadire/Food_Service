package com.foodapp.service;

import com.foodapp.entity.User;

public interface UserService {

    boolean registerUser(User user, String token);

    User login(String email, String password);

    User getUserByEmail(String email);

}
