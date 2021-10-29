package com.storemanager.service;

import com.storemanager.model.Inventory;
import com.storemanager.repository.InventoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Page<Inventory> getInventory() {
        Pageable pageable = PageRequest.of(0, 2);
        return inventoryRepository.findAll(pageable);
    }

    public Optional<Inventory> getOneInventory(UUID id) {
        return exists(id);
    }

    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(UUID id) {
        exists(id);
        inventoryRepository.deleteById(id);
    }

    public Inventory updateInventory(UUID idInventory, Inventory updateOneInventory) {
        exists(idInventory);
        var inventoryById = inventoryRepository.getById(idInventory);
        final var forUpdateQuantity = updateOneInventory.getQuantity();
        final var forUpdatePrice = updateOneInventory.getPrice();
        if (areNotEqual(inventoryById.getQuantity(), forUpdateQuantity)) inventoryById.setQuantity(forUpdateQuantity);
        if (areNotEqual(inventoryById.getPrice(), forUpdatePrice)) inventoryById.setPrice(forUpdatePrice);
        return inventoryRepository.save(inventoryById);
    }

    private <T> boolean areNotEqual(T first, T second) {
        return second != null && !Objects.equals(first, second);
    }

    private Optional<Inventory> exists(UUID id) {
        var byId = inventoryRepository.findById(id);
        if (byId.isEmpty()) throw new IllegalStateException("Inventory by ID : " + id + " does not exists");
        return byId;
    }

}
