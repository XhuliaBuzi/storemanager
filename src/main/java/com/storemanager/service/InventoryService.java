package com.storemanager.service;

import com.storemanager.model.Inventory;
import com.storemanager.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getInventory() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> getOneInventory(UUID id) {
        exists(id);
        return inventoryRepository.findById(id);
    }

    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(UUID id) {
        exists(id);
        inventoryRepository.deleteById(id);
    }

    private void exists(UUID id) {
        if (!inventoryRepository.existsById(id))
            throw new IllegalStateException("Inventory by ID : " + id + " does not exists");
    }
}
