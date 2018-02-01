package com.foodorderingapp.controller;

import com.foodorderingapp.commons.WebUrlConstant;
import com.foodorderingapp.dto.LoginDto;
import com.foodorderingapp.dto.UserDto;
import com.foodorderingapp.model.User;
import com.foodorderingapp.service.NotificationService;
import com.foodorderingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebUrlConstant.User.USER_API)
public class UserController {

    private final UserService userService;
    private final NotificationService notificationService;

    @Autowired
    public UserController(UserService userService, NotificationService notificationService) {
        this.userService = userService;
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<User> add(@RequestBody @Valid UserDto userDto) {
        User user=userService.addUser(userDto);
        notificationService.sendSimpleMessage(user.getEmail(),"Hello World","Happy Registration");
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(value = "/verify")
    public LoginDto verifyUser(@RequestBody @Valid LoginDto loginDto) {
        LoginDto loginDto1 = userService.verifyUser(loginDto.getUserPassword(), loginDto.getEmail());
        return loginDto1;
    }

    @GetMapping(value = "/{userId}")
    public User getUser(@PathVariable("userId") int userId) {
            return userService.getUser(userId);
    }

    @GetMapping(value = "/signUp-success")
    public String signUpSuccess(){
        return "";
    }

}
