package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.dto.*;
import com.foodorderingapp.exception.NotFoundException;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.User;
import com.foodorderingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final OrderDetailDAO orderDetailDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, OrderDetailDAO orderDetailDAO) {
        this.userDAO = userDAO;
        this.orderDetailDAO = orderDetailDAO;
    }

    public void addUser(UserDto userDto) {

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setContactNo(userDto.getContactNo());
        user.setUserPassword(userDto.getUserPassword());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setBalance(1200);
        user.setUserRole("user");
        User user1 = userDAO.getUserByEmailId(user);
        if (user1 != null) {
            throw new NotFoundException("plz rewite email");

        } else if (user1 == null) {
            userDAO.addUser(user);
        }
    }

    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    public LoginDto verifyUser(String userPassword,String email) {

        User user1 = userDAO.getUserByEmail(userPassword,email);
        LoginDto loginDto1 = new LoginDto();
        loginDto1.setId(user1.getUserId());
        loginDto1.setFirstName(user1.getFirstName());
        loginDto1.setMiddleName(user1.getMiddleName());
        loginDto1.setLastName(user1.getLastName());
        loginDto1.setContactNo(user1.getContactNo());
        loginDto1.setEmail(user1.getEmail());
        loginDto1.setAddress(user1.getAddress());
        loginDto1.setUserRole(user1.getUserRole());
        loginDto1.setBalance(user1.getBalance());

        if (user1 == null) {
            throw new NotFoundException("user not exits");
        } else {
            return loginDto1;
        }
    }

    public User getUser(int userId) {
        return userDAO.getUser(userId);
    }

    public void update(User user, int userId) {
        User user1 = userDAO.getUser(userId);
        user1.setBalance(user.getBalance());
        userDAO.update(user1);
    }
}
