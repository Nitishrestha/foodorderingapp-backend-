package com.foodorderingapp.dao;

import com.foodorderingapp.dto.UserListMapperDto;
import com.foodorderingapp.model.User;

import java.util.List;

public interface UserDAO {
     User addUser(User user);
     List<User> getUsers();
     User getUser(int userId);
    User getUserByEmail(String userPassword,String email);
    List<UserListMapperDto> getByUserId(int userId);
    User getUserByEmailId(String email);
    void update(User user);
}
