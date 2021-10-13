package com.storemanager.controller;

import com.storemanager.model.Inventory;
import com.storemanager.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Inventory> getInventory() {
        return inventoryService.getInventory();
    }

    @GetMapping(path = "/{id}")
    public Optional<Inventory> getOneInventory(@PathVariable("id") Long id) {
        return inventoryService.getOneInventory(id);
    }

    @PostMapping(path = "/add")
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryService.addInventory(inventory);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteInventory(@PathVariable("id") Long id) {
        inventoryService.deleteInventory(id);
    }
}
