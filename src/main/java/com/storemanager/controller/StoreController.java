package com.storemanager.controller;

import com.storemanager.model.Store;
import com.storemanager.service.StoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/store")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public List<Store> getStore() {
        return storeService.getService();
    }

    @GetMapping(path = "/add")
    public void registerNewUser(@RequestBody Store store) {
        storeService.addNewUser(store);
    }
}
