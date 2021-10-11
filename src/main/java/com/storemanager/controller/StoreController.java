package com.storemanager.controller;

import com.storemanager.model.Store;
import com.storemanager.service.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/store")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public List<Store> GetStore() {
        return storeService.GetService();
    }

    @PostMapping(path = "/add")
    public void RegisterNewUser(@RequestBody Store store) {
        storeService.AddNewUser(store);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void DeleteStore(@PathVariable("id") Long id) {
        storeService.DeleteStore(id);
    }
}
