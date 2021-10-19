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

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {
    private StoreService storeService;
    private Store store;
    @Mock
    private StoreRepository storeRepository;

    @BeforeEach
    public void init() {
        storeService = new StoreService(storeRepository);
        store = new Store(UUID.randomUUID(), "", "", "", null, null);
    }

    @Test
    void shouldReturnServiceTest() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Store> page = new PageImpl<>(Collections.singletonList(store), pageable, 1);

        Mockito.when(storeRepository.findAll(pageable)).thenReturn(page);

        assertNotNull(storeService.getService());
    }

    @Test
    void shouldReturnOneUserTest() {
        UUID uuid = UUID.randomUUID();
        Mockito.when(storeRepository.existsById(uuid)).thenReturn(true);
        Mockito.when(storeRepository.findById(uuid)).thenReturn(java.util.Optional.of(store));

        assertNotNull(storeService.getOneUser(uuid));
    }

    @Test
    void shouldaddNewUserTRUETest() {

    }

    @Test
    void shouldAddNewUserTest() {
        Mockito.when(storeRepository.save(store)).thenReturn(store);

        assertNotNull(storeService.addNewUser(store));
    }

    @Test
    void shouldDeleteStoreTest() {
    }
}