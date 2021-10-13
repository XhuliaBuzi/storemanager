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
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/{email}")
    public Optional<User> getOneUser(@PathVariable("email") String email) {
        return userService.getOneUser(email);
    }

    @PostMapping(path = "/add")
    public User registerNewUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @DeleteMapping(path = "/{email}")
    public void deleteUser(@PathVariable("email") String email) {
        userService.deleteUser(email);
    }
}
