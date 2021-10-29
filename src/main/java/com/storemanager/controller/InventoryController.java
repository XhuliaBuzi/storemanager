package com.storemanager.controller;

import com.storemanager.model.Inventory;
import com.storemanager.service.InventoryService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public Page<Inventory> getInventory() {
        return inventoryService.getInventory();
    }

    @GetMapping(path = "/{id}")
    public Optional<Inventory> getOneInventory(@PathVariable("id") UUID id) {
        return inventoryService.getOneInventory(id);
    }

    @PostMapping
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryService.addInventory(inventory);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteInventory(@PathVariable("id") UUID id) {
        inventoryService.deleteInventory(id);
    }

    @PatchMapping(path = "/{idInventory}")
    public Inventory updateInventory(@PathVariable("idInventory")UUID idInventory,@RequestBody Inventory inventory){
        return inventoryService.updateInventory(idInventory,inventory);
    }
}
