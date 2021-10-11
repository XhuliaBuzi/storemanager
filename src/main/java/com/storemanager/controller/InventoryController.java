package com.storemanager.controller;

import com.storemanager.model.Inventory;
import com.storemanager.service.InventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @GetMapping (path = "/add")
    public void addInventary(@RequestBody Inventory inventory){
        inventoryService.addInventory(inventory);
    }
}
