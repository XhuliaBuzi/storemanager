package com.storemanager.service;

import com.storemanager.model.Inventory;
import com.storemanager.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> GetInventory() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> GetOneInventory(Long id) {
        return inventoryRepository.findById(id);
    }

    public void AddInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    public void DeleteInventory(Long id) {
        boolean exists = inventoryRepository.existsById(id);
        if (!exists) throw new IllegalStateException("Inventory with ID : " + id + " does not exists");
        inventoryRepository.deleteById(id);
    }
}
