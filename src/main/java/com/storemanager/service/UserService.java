package com.storemanager.service;

import com.storemanager.model.User;
import com.storemanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getOneUser(String email) {
        exists(email);
        return userRepository.findById(email);
    }

    public User addNewUser(User user) {
        Optional<User> userByEmail = userRepository.findById(user.getEmail());
        if (userByEmail.isPresent()) throw new IllegalStateException("Email taken");
        return userRepository.save(user);
    }

    public void deleteUser(String email) {
        exists(email);
        userRepository.deleteById(email);
    }

    private void exists(String email) {
        boolean exists = userRepository.existsById(email);
        if (!exists) throw new IllegalStateException("User by Email : " + email + " does not exists.");
    }
}
