package com.storemanager.service;

import com.storemanager.model.Store;
import com.storemanager.repository.StoreRepository;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {
    private StoreService storeService;
    private Store store;
    private UUID uuid;
    @Mock
    private StoreRepository storeRepository;

    @BeforeEach
    public void init() {
        storeService = new StoreService(storeRepository);
        uuid = UUID.randomUUID();
        store = new Store(UUID.randomUUID(), "", "", "", null, null);
    }

    @Test
    void shouldReturnStoreTest() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Store> page = new PageImpl<>(Collections.singletonList(store), pageable, 1);

        Mockito.when(storeRepository.findAll(pageable)).thenReturn(page);

        assertNotNull(storeService.getStore());
    }

    @Test
    void shouldReturnOneStoreTest() {
        Mockito.when(storeRepository.existsById(uuid)).thenReturn(true);
        Mockito.when(storeRepository.findById(uuid)).thenReturn(java.util.Optional.of(store));

        assertNotNull(storeService.getOneStore(uuid));
    }

    @Test
    void shouldReturnOneStoreExceptionTest() {
        Mockito.when(storeRepository.existsById(uuid)).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> storeService.getOneStore(uuid));
    }

    @Test
    void shouldAddNewStoreTest() {
        Mockito.when(storeRepository.save(store)).thenReturn(store);

        assertNotNull(storeService.addNewStore(store));
    }

    @Test
    void shouldAddNewStoreExceptionTest() {
        Mockito.when(storeRepository.findByName("")).thenReturn(java.util.Optional.ofNullable(store));

        assertThrows(IllegalStateException.class, () -> storeService.addNewStore(store));
    }

    @Test
    void shouldDeleteStoreTest() {
        Mockito.when(storeRepository.existsById(uuid)).thenReturn(true);

        storeService.deleteStore(uuid);
        Mockito.verify(storeRepository, Mockito.atLeastOnce()).deleteById(uuid);
    }
}