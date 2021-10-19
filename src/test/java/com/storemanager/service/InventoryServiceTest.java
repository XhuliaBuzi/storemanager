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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {
    private InventoryService inventoryService;
    private Inventory inventory;
    @Mock
    InventoryRepository inventoryRepository;

    @BeforeEach
    public void inti() {
        inventoryService = new InventoryService(inventoryRepository);
        inventory = new Inventory(null, UUID.randomUUID(), 0, 0f, null, null);
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
        UUID uuid=UUID.randomUUID();
        Mockito.when(inventoryRepository.existsById(uuid)).thenReturn(true);
        Mockito.when(inventoryRepository.findById(uuid)).thenReturn(java.util.Optional.ofNullable(inventory));

        assertNotNull(inventoryService.getOneInventory(uuid));
    }

    @Test
    void shouldAddInventoryFindTest() {
    }

    @Test
    void shouldAddInventoryTest() {
        Mockito.when(inventoryRepository.save(inventory)).thenReturn(inventory);

        assertNotNull(inventoryService.addInventory(inventory));
    }

    @Test
    void shouldDeleteInventoryTest() {
    }
}