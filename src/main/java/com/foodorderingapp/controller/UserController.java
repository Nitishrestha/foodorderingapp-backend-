package com.foodorderingapp.controller;

import com.foodorderingapp.dto.LoginDto;
import com.foodorderingapp.dto.UserDto;
import com.foodorderingapp.model.User;
import com.foodorderingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseBody
    public void addUser(@RequestBody UserDto userDto) {

        userService.addUser(userDto);
    }

    @GetMapping
    @ResponseBody
    public List<User> getUsers() {

        return userService.getUsers();
    }

    @PostMapping(value="/verify")
    @ResponseBody
    public LoginDto verifyUser(@RequestBody LoginDto loginDto){

        return userService.verifyUser(loginDto);

    }

}
