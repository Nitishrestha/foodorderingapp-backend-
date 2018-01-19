package com.foodorderingapp.dao;

import com.foodorderingapp.dto.UserListDto;
import com.foodorderingapp.dto.UserListMapperDto;
import com.foodorderingapp.model.User;

import java.util.List;
import java.util.Set;

public interface UserDAO {
     Boolean addUser(User user);
     List<User> getUsers();
     User getUser(int userId);
    User getUserByEmail(String userPassword,String email);
    List<UserListMapperDto> getByUserId(int userId);
    User getUserByEmailId(User user);
    void update(User user);
}
