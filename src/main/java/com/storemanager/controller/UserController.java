package com.storemanager.controller;

import com.storemanager.model.User;
import com.storemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/user")
public class UserController {
    private final UserService userService;
@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> GetUsers(){
    return userService.GetUsers();
    }
}
