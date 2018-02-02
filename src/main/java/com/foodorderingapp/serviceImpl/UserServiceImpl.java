package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.dto.LoginDto;
import com.foodorderingapp.dto.UserDto;
import com.foodorderingapp.dto.UserListMapperDto;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.exception.UserConflictException;
import com.foodorderingapp.model.User;
import com.foodorderingapp.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, BCryptPasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(UserDto userDto) {
        User user1 = userDAO.getUserByEmailId(userDto.getEmail());
        if (user1 == null) {
            User user = new User();
            user.setFirstName(userDto.getFirstName());
            user.setMiddleName(userDto.getMiddleName());
            user.setLastName(userDto.getLastName());
            user.setContactNo(userDto.getContactNo());
            user.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
            user.setAddress(userDto.getAddress());
            user.setEmail(userDto.getEmail());
            userDAO.addUser(user);
            return user;
        } else  {
            throw new DataNotFoundException("plz rewite email");
        }
    }

    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    public LoginDto verifyUser(String userPassword, String email) {
        User user = userDAO.getUserByEmailId(email);
        if (user == null) {
            throw new DataNotFoundException("username or password didn't match!!!");
        } else if (passwordEncoder.matches(userPassword, user.getUserPassword())) {
            LoginDto loginDto1 = new LoginDto();
            loginDto1.setId(user.getUserId());
            BeanUtils.copyProperties(user, loginDto1);
            return loginDto1;
        } else
            throw new UserConflictException("username or password didn't match!!!");
    }

    public User getUser(int userId) {
        User user = userDAO.getUser(userId);
        if (user == null) {
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
