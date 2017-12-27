package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.dto.LoginDto;
import com.foodorderingapp.dto.UserDto;
import com.foodorderingapp.model.User;
import com.foodorderingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    public void addUser(UserDto userDto) {

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setContactNo(userDto.getContactNo());
        user.setUserPassword(userDto.getUserPassword());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setBalance(userDto.getBalance());
        user.setUserRole("user");

        User user1 = userDAO.getUserByEmailId(user);

        if (user1 != null) {

            throw new IllegalArgumentException("plz rewite email");

        } else if (user1 == null) {

            userDAO.addUser(user);
        }
    }

    public List<User> getUsers() {

        return userDAO.getUsers();
    }


    public LoginDto verifyUser(LoginDto loginDto) {

        User user = new User();
        user.setEmail(loginDto.getEmail());
        user.setUserPassword(loginDto.getUserPassword());


        User user1 = userDAO.getUserByEmail(user);

        LoginDto loginDto1 = new LoginDto();
        loginDto1.setEmail(user1.getEmail());
        loginDto1.setId(user1.getUserId());
        loginDto1.setFirstName(user1.getFirstName());
        loginDto1.setMiddleName(user1.getMiddleName());
        loginDto1.setLastName(user1.getLastName());


        if (user1 == null) {

            throw new IllegalArgumentException("user not exits");

        } else {

            return loginDto1;
        }
    }

    public User getUser(int userId) {
        return userDAO.getUser(userId);
    }

}
