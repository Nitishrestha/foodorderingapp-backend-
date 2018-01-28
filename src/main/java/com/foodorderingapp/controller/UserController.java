package com.foodorderingapp.controller;

import com.foodorderingapp.commons.WebUrlConstant;
import com.foodorderingapp.dto.LoginDto;
import com.foodorderingapp.dto.UserDto;
import com.foodorderingapp.dto.UserListDto;
import com.foodorderingapp.exception.NotFoundException;
import com.foodorderingapp.model.User;
import com.foodorderingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(WebUrlConstant.User.USER_API)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return new ResponseEntity<String>("user added", HttpStatus.OK);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(value = "/verify")
    public LoginDto verifyUser(@RequestBody LoginDto loginDto) {
        LoginDto loginDto1 = userService.verifyUser(loginDto.getUserPassword(), loginDto.getEmail());
        return loginDto1;
    }

    @GetMapping(value = "/{userId}")
    public User getUser(@PathVariable("userId") int userId) {
            return userService.getUser(userId);
    }
}
