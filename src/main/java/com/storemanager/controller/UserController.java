package com.storemanager.controller;

import com.storemanager.model.User;
import com.storemanager.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping(path = "/{id}")
    public Optional<User> getOneUser(@PathVariable("id") UUID id) {
        return userService.getOneUser(id);
    }

    @PostMapping
    public User registerNewUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
    }
}
