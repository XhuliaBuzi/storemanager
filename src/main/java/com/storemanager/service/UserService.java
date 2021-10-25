package com.storemanager.service;

import com.storemanager.model.User;
import com.storemanager.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getUsers(String sort) {
        Pageable pageable = PageRequest.of(0, 2, Sort.by(sort));
        return userRepository.findAll(pageable);
    }

    public Optional<User> getOneUser(UUID id) {
        return exists(id);
    }

    public User addNewUser(User user) {
        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());
        if (userByEmail.isPresent()) throw new IllegalStateException("Email taken.");
        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        userRepository.delete(exists(id).get());
    }

    @Transactional
    public void updateUser(UUID userID, String email, String password) {
        User user = exists(userID).get();
        if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
            user.setEmail(email);
        }
        if (password != null && password.length() > 0 && !Objects.equals(user.getEmail(), password)) {
            user.setPassword(password);
        }
    }

    private Optional<User> exists(UUID id) {
        Optional<User> userByID = userRepository.findById(id);
        if (userByID.isEmpty()) throw new IllegalStateException("User by Email : " + id + " does not exists.");
        return userByID;
    }

}
