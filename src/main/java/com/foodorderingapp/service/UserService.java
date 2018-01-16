package com.foodorderingapp.service;

import com.foodorderingapp.dto.LoginDto;
import com.foodorderingapp.dto.OrderListDto;
import com.foodorderingapp.dto.UserDto;
import com.foodorderingapp.dto.UserListDto;
import com.foodorderingapp.model.User;
import sun.rmi.runtime.Log;

import java.util.List;

public interface UserService {

    void addUser(UserDto userDto);
    List<User> getUsers();
    LoginDto verifyUser(LoginDto loginDto);
    User getUser(int userId);
    void update(User user,int userId);
}
