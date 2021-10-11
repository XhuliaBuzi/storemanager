package com.storemanager.controller;

import com.storemanager.model.User;
import com.storemanager.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/one/{email}")
    public Optional<User> GetOneUser(@PathVariable("email") String email) {
        return userService.GetOneUser(email);
    }

    @PostMapping(path = "/add")
    public List<User> RegisterNewUser(@RequestBody User user) {
        userService.AddNewUser(user);
        return GetUsers();
    }

    @DeleteMapping(path = "/delete/{email}")
    public List<User> DeleteUser(@PathVariable("email") String email) {
        userService.DeleteUser(email);
        return GetUsers();
    }
}
