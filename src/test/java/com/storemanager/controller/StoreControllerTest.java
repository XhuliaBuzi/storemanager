package com.storemanager.controller;

import com.storemanager.model.Store;
import com.storemanager.service.StoreService;
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
class StoreControllerTest {
    private StoreController storeController;
    private UUID uuid;
    private Store store;

    @Mock
    StoreService storeService;

    @BeforeEach
    void setUp() {
        storeController = new StoreController(storeService);
        uuid = UUID.randomUUID();
        store = new Store(UUID.randomUUID(), "", "", "", null, null);
    }

    @Test
    void getStoreTest() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Store> page = new PageImpl<>(Collections.singletonList(store), pageable, 1);

        Mockito.when(storeService.getStore()).thenReturn(page);

        assertNotNull(storeController.getStore());
    }

    @Test
    void getOneStoreTest() {
        assertNotNull(storeController.getOneStore(uuid));
    }

    @Test
    void registerNewStoreTest() {
        Mockito.when(storeService.addNewStore(store)).thenReturn(store);

        assertNotNull(storeController.registerNewStore(store));
    }

    @Test
    void deleteStoreTest() {
        storeController.deleteStore(uuid);
        Mockito.verify(storeService, Mockito.atLeastOnce()).deleteStore(uuid);
    }
}