package com.storemanager.controller;

import com.storemanager.model.Inventory;
import com.storemanager.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping(path = "/add")
    public void AddInventory(@RequestBody Inventory inventory) {
        inventoryService.AddInventory(inventory);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void DeleteInventory(@PathVariable("id") Long id) {
        inventoryService.DeleteInventory(id);
    }
}
