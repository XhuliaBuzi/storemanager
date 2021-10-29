package com.storemanager.controller;

import com.storemanager.model.Store;
import com.storemanager.service.StoreService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/store")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public Page<Store> getStore() {
        return storeService.getStore();
    }

    @GetMapping(path = "/{id}")
    public Optional<Store> getOneStore(@PathVariable("id") UUID id) {
        return storeService.getOneStore(id);
    }

    @PostMapping
    public Store registerNewStore(@RequestBody Store store) {
        return storeService.addNewStore(store);
    }

    @PatchMapping(path="/{idStore}")
    public Store updateStore(@PathVariable("idStore") UUID idStore, @RequestBody Store store) {
        return storeService.updateStore(idStore,store);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteStore(@PathVariable("id") UUID id) {
        storeService.deleteStore(id);
    }
}
