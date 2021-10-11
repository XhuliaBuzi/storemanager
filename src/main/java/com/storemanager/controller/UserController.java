package com.storemanager.controller;

import com.storemanager.model.User;
import com.storemanager.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> GetUsers() {
        return userService.GetUsers();
    }

    @PostMapping(path = "/add")
    public void RegisterNewUser(@RequestBody User user) {
        userService.AddNewUser(user);
    }

    @DeleteMapping(path = "/delete/{email}")
    public void DeleteUser(@PathVariable("email") String email) {
        userService.DeleteUser(email);
    }
}
