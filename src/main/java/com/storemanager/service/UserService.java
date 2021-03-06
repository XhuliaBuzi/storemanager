package com.storemanager.service;

import com.storemanager.model.User;
import com.storemanager.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getUsers(String sort) {
        Pageable pageable= PageRequest.of(0,2,Sort.by(sort));
        return userRepository.findAll(pageable);
    }

    public Optional<User> getOneUser(UUID id) {
        exists(id);
        return userRepository.findById(id);
    }

    public User addNewUser(User user) {
        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());
        if (userByEmail.isPresent()) throw new IllegalStateException("Email taken.");
        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        exists(id);
        userRepository.deleteById(id);
    }

    private void exists(UUID id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) throw new IllegalStateException("User by Email : " + id + " does not exists.");
    }
}
