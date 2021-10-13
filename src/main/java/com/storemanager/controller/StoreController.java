package com.storemanager.controller;

import com.storemanager.model.Store;
import com.storemanager.service.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/{id}")
    public Optional<Store> getOneStore(@PathVariable("id") Long id) {
        return storeService.getOneUser(id);
    }

    @PostMapping(path = "/add")
    public Store registerNewUser(@RequestBody Store store) {
        return storeService.addNewUser(store);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteStore(@PathVariable("id") Long id) {
        storeService.deleteStore(id);
    }
}
