package com.storemanager.controller;

import com.storemanager.model.Inventory;
import com.storemanager.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/inventory")
public class InventoryController {
    private final InventoryService inventoryService;
@Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    @GetMapping
    public List<Inventory> GetInventory(){
    return inventoryService.GetInventory();
    }
}
