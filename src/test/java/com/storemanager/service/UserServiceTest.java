package com.storemanager.service;

import com.storemanager.model.User;
import com.storemanager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository; // this guy is just pretending to know what to do .. but he doesnt

    @BeforeEach
    public void init() {
        userService = new UserService(userRepository);
    }

    @Test
    public void shouldReturnUserbyEmail() {

        Pageable pageable = PageRequest.of(0, 2, Sort.by("email"));
        Page<User> page = new PageImpl<>(Collections.singletonList(new User(UUID.randomUUID(), "", "", null)), pageable, 1);

        Mockito.when(userRepository.findAll(pageable)).thenReturn(page);

        assertNotNull(userService.getUsers("email"));
    }

    @Test
    void shouldReturnOneUserTest() {
        UUID uuid = UUID.randomUUID();
        Mockito.when(userRepository.existsById(uuid)).thenReturn(true);
        Mockito.when(userRepository.findById(uuid)).thenReturn(Optional.of(new User(UUID.randomUUID(), "", "", null)));

        assertNotNull(userService.getOneUser(uuid));

    }

    @Test
    void shouldAddNewUserTest() {
        User user = new User(UUID.randomUUID(), "", "", null);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        assertNotNull(userService.addNewUser(user));
    }
    @Test
    void shouldAddNewUserFindTest() {
        User user = new User(UUID.randomUUID(), "", "", null);
        Mockito.when(userRepository.findByEmail("")).thenReturn(Optional.of(user));

        assertNotNull(userService.addNewUser(user));
    }

    @Test
    void shouldDeleteUserTest() {
    }
}