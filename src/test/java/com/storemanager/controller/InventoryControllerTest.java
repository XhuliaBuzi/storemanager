package com.storemanager.controller;

import com.storemanager.model.Inventory;
import com.storemanager.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class InventoryControllerTest {
    private InventoryController inventoryController;
    private UUID uuid;
    private Inventory inventory;

    @Mock
    InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        inventoryController = new InventoryController(inventoryService);
        uuid = UUID.randomUUID();
        inventory = new Inventory(UUID.randomUUID(), 0, 0f, null, null, null);
    }

    @Test
    void getInventoryTest() {
        Page<Inventory> page = new PageImpl<>(Collections.singletonList(inventory));
        Mockito.when(inventoryService.getInventory()).thenReturn(page);

        assertNotNull(inventoryController.getInventory());
    }

    @Test
    void getOneInventoryTest() {
        Mockito.when(inventoryService.getOneInventory(uuid)).thenReturn(Optional.of(inventory));

        assertNotNull(inventoryController.getOneInventory(uuid));
    }

    @Test
    void addInventoryTest() {
        Mockito.when(inventoryService.addInventory(inventory)).thenReturn(inventory);

        assertNotNull(inventoryController.addInventory(inventory));
    }

    @Test
    void deleteInventoryTest() {
        inventoryController.deleteInventory(uuid);
        Mockito.verify(inventoryService, Mockito.atLeastOnce()).deleteInventory(uuid);
    }

    @Test
    void shouldUpdateInventoryTest() {
        inventory.setQuantity(1);
        inventory.setPrice(1.1f);
        Mockito.when(inventoryService.updateInventory(uuid,inventory)).thenReturn(inventory);

        assertNotNull(inventoryController.updateInventory(uuid, inventory));
    }
}