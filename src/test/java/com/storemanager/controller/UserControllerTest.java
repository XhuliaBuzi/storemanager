package com.storemanager.controller;

import com.storemanager.model.User;
import com.storemanager.service.UserService;
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
import static org.mockito.Mockito.atLeastOnce;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    private UserController userController;
    private User user;
    private UUID uuid;

    @Mock
    UserService userService;

    @BeforeEach
    void setUp() {
        userController = new UserController(userService);
        uuid = UUID.randomUUID();
        user = new User(UUID.randomUUID(), "", "", null);
    }

    @Test
    void getUsersTest() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("email"));
        Page<User> page = new PageImpl<>(Collections.singletonList(user), pageable, 1);

        Mockito.when(userService.getUsers("email")).thenReturn(page);

        assertNotNull(userController.getUsers("email"));
    }

    @Test
    void getOneUserTest() {
        Mockito.when(userService.getOneUser(uuid)).thenReturn(Optional.of(user));

        assertNotNull(userController.getOneUser(uuid));
    }

    @Test
    void registerNewUserTest() {
        Mockito.when(userService.addNewUser(user)).thenReturn(user);

        assertNotNull(userController.registerNewUser(user));
    }

    @Test
    void deleteUserTest() {
        userController.deleteUser(uuid);
        Mockito.verify(userService, atLeastOnce()).deleteUser(uuid);
    }

    @Test
    void shouldUpdateUserTest() {
        user.setEmail("test@gmail.com");
        user.setPassword("f");
        Mockito.when(userService.updateUser(uuid,user)).thenReturn(user);

        assertNotNull(userController.updateUser(uuid, user));
    }
}