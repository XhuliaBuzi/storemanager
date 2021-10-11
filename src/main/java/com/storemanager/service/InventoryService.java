package com.storemanager.service;

import com.storemanager.model.Inventory;
import com.storemanager.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getInventory() {
        return inventoryRepository.findAll();
    }

    public void addInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }
}
