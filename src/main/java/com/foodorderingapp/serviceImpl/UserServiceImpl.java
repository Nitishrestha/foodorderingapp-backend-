package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.dto.LoginDto;
import com.foodorderingapp.dto.UserDto;
import com.foodorderingapp.dto.UserListMapperDto;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.User;
import com.foodorderingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User addUser(UserDto userDto) {


        User user1 = userDAO.getUserByEmailId(userDto.getEmail());
        if (user1 != null) {
            throw new DataNotFoundException("plz rewite email");

        } else if(user1==null) {
            User user = new User();
            user.setFirstName(userDto.getFirstName());
            user.setMiddleName(userDto.getMiddleName());
            user.setLastName(userDto.getLastName());
            user.setContactNo(userDto.getContactNo());
            user.setUserPassword(userDto.getUserPassword());
            user.setAddress(userDto.getAddress());
            user.setEmail(userDto.getEmail());
            user.setUserRole("user");
            userDAO.addUser(user);
            return user;
        }
        return user1;
    }

    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    public LoginDto verifyUser(String userPassword,String email) {

        User user1 = userDAO.getUserByEmail(userPassword,email);

        if (user1 == null) {
            throw new DataNotFoundException("user not exits");
        } else {
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

            return loginDto1;
        }
    }

    public User getUser(int userId) {
        User user= userDAO.getUser(userId);
        if(user==null){
            throw new DataNotFoundException("user not found");
        }
        return user;
    }

    public User update(User user) {
        userDAO.update(user);
        return user;
    }

    @Override
    public List<UserListMapperDto> getByUserId(int userId) {
       return userDAO.getByUserId(userId);
    }
}
