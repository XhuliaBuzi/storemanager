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

    public List<User> GetUsers() {
        return userRepository.findAll();
    }

    public Optional<User> GetOneUser(String email) {
        Exists(email);
        return userRepository.findById(email);
    }

    public void AddNewUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()) throw new IllegalStateException("Email taken");
        userRepository.save(user);
    }

    public void DeleteUser(String email) {
        Exists(email);
        userRepository.deleteById(email);
    }

    private void Exists(String email) {
        boolean exists = userRepository.existsById(email);
        if (!exists) throw new IllegalStateException("User by Email : " + email + " does not exists.");
    }
}
