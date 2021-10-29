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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeastOnce;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService userService;
    private UUID uuid;
    private User user;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        userService = new UserService(userRepository);
        uuid = UUID.randomUUID();
        user = new User(UUID.randomUUID(), "", "", null);
    }

    @Test
    public void shouldReturnUserByEmailTest() {

        Pageable pageable = PageRequest.of(0, 2, Sort.by("email"));
        Page<User> page = new PageImpl<>(Collections.singletonList(user), pageable, 1);
        Mockito.when(userRepository.findAll(pageable)).thenReturn(page);
        assertNotNull(userService.getUsers("email"));
    }

    @Test
    void shouldReturnOneUserTest() {
        Mockito.when(userRepository.findById(uuid)).thenReturn(Optional.of(user));

        assertNotNull(userService.getOneUser(uuid));
    }

    @Test
    void shouldReturnOneUserExceptionTest() {
        assertThrows(IllegalStateException.class, () -> userService.getOneUser(uuid));
    }

    @Test
    void shouldAddNewUserTest() {
        Mockito.when(userRepository.save(user)).thenReturn(user);

        assertNotNull(userService.addNewUser(user));
    }

    @Test
    void shouldAddNewUserExceptionTest() {
        Mockito.when(userRepository.findByEmail("")).thenReturn(Optional.of(user));

        assertThrows(IllegalStateException.class, () -> userService.addNewUser(user));
    }

    @Test
    void shouldDeleteUserTest() {
        Mockito.when(userRepository.findById(uuid)).thenReturn(Optional.of(user));

        userService.deleteUser(uuid);
        Mockito.verify(userRepository, atLeastOnce()).deleteById(uuid);
    }

    @Test
    void shouldUpdateUserTest() {
        Mockito.when(userRepository.findById(uuid)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.getById(uuid)).thenReturn(user);

        user.setEmail("test@gmail.com");
        user.setPassword("f");
        Mockito.when(userRepository.save(user)).thenReturn(user);

        assertNotNull(userService.updateUser(uuid, user));
    }

}