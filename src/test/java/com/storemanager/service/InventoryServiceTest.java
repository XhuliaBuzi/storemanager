package com.storemanager.service;

import com.storemanager.model.Inventory;
import com.storemanager.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {
    private InventoryService inventoryService;
    private Inventory inventory;
    private UUID uuid;
    @Mock
    InventoryRepository inventoryRepository;

    @BeforeEach
    public void inti() {
        inventoryService = new InventoryService(inventoryRepository);
        uuid = UUID.randomUUID();
        inventory = new Inventory(UUID.randomUUID(), 0, 0f, null, null, null);
    }

    @Test
    void shouldGetInventoryTest() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Inventory> page = new PageImpl<>(Collections.singletonList(inventory));
        Mockito.when(inventoryRepository.findAll(pageable)).thenReturn(page);

        assertNotNull(inventoryService.getInventory());
    }

    @Test
    void shouldGetOneInventoryTest() {
        Mockito.when(inventoryRepository.findById(uuid)).thenReturn(Optional.of(inventory));

        assertNotNull(inventoryService.getOneInventory(uuid));
    }

    @Test
    void shouldGetOneInventoryExceptionTest() {
        assertThrows(IllegalStateException.class,()-> inventoryService.getOneInventory(uuid));
    }

    @Test
    void shouldAddInventoryTest() {
        Mockito.when(inventoryRepository.save(inventory)).thenReturn(inventory);

        assertNotNull(inventoryService.addInventory(inventory));
    }

    @Test
    void shouldDeleteInventoryTest() {
        Mockito.when(inventoryRepository.findById(uuid)).thenReturn(Optional.of(inventory));

        inventoryService.deleteInventory(uuid);
        Mockito.verify(inventoryRepository,Mockito.atLeastOnce()).deleteById(uuid);
    }

    @Test
    void shouldUpdateInventoryTest() {
        Mockito.when(inventoryRepository.findById(uuid)).thenReturn(Optional.of(inventory));
        Mockito.when(inventoryRepository.getById(uuid)).thenReturn(inventory);

        inventory.setQuantity(1);
        inventory.setPrice(1.1f);
        Mockito.when(inventoryRepository.save(inventory)).thenReturn(inventory);

        assertNotNull(inventoryService.updateInventory(uuid, inventory));
    }
}
