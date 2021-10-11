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
    public List<Store> RegisterNewUser(@RequestBody Store store) {
        storeService.AddNewUser(store);
        return GetStore();
    }

    @DeleteMapping(path = "/delete/{id}")
    public List<Store> DeleteStore(@PathVariable("id") Long id) {
        storeService.DeleteStore(id);
        return GetStore();
    }
}
