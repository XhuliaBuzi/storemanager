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
    public List<Inventory> GetInventory() {
        return inventoryService.GetInventory();
    }

    @GetMapping(path = "/find/{id}")
    public Optional<Inventory> GetOneInventory(@PathVariable("id") Long id) {
        return inventoryService.GetOneInventory(id);
    }

    @PostMapping(path = "/add")
    public List<Inventory> AddInventory(@RequestBody Inventory inventory) {
        inventoryService.AddInventory(inventory);
        return GetInventory();
    }

    @DeleteMapping(path = "/delete/{id}")
    public List<Inventory> DeleteInventory(@PathVariable("id") Long id) {
        inventoryService.DeleteInventory(id);
        return GetInventory();
    }
}
