package com.foodorderingapp.dao;

import com.foodorderingapp.model.User;

import java.util.List;

public interface UserDAO {

    Boolean addUser(User user);
    List<User> getUsers();
    User getUser(int userId);
    User getUserByEmail(User user);
    User getUserByEmailId(User user);

}
