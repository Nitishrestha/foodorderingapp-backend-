package com.foodorderingapp.service;

import com.foodorderingapp.dto.LoginDto;
import com.foodorderingapp.dto.UserDto;
import com.foodorderingapp.model.User;

import java.util.List;

public interface UserService {

    void addUser(UserDto userDto);
    List<User> getUsers();
    LoginDto verifyUser(LoginDto loginDto);
    User getUser(int userId);

}
