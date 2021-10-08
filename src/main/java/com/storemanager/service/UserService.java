package com.storemanager.service;

import com.storemanager.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    public List<User> GetUsers(){
        return List.of(new User("xhuliabuzzi@gmail.com","1234567"));
    }
}
