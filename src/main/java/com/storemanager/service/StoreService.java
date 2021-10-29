package com.storemanager.service;

import com.storemanager.model.Store;
import com.storemanager.repository.StoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Page<Store> getStore() {
        Pageable pageable = PageRequest.of(0, 2);
        return storeRepository.findAll(pageable);
    }

    public Optional<Store> getOneStore(UUID id) {
        return exists(id);
    }

    public Store addNewStore(Store store) {
        var userById = storeRepository.findByName(store.getName());
        if (userById.isPresent()) throw new IllegalStateException("ID taken");
        return storeRepository.save(store);
    }

    public void deleteStore(UUID id) {
        exists(id);
        storeRepository.deleteById(id);
    }

    public Store updateStore(UUID idStore, Store forUpdateStore) {
        exists(idStore);
        var storeById = storeRepository.getById(idStore);
        final var forUpdateName = forUpdateStore.getName();
        final var forUpdateAddress = forUpdateStore.getAddress();
        final var forUpdateContact = forUpdateStore.getContact();
        if (areNotEqual(storeById.getName(), forUpdateName)) storeById.setName(forUpdateName);
        if (areNotEqual(storeById.getAddress(), forUpdateAddress)) storeById.setAddress(forUpdateAddress);
        if (areNotEqual(storeById.getContact(), forUpdateContact)) storeById.setContact(forUpdateContact);
        return storeRepository.save(storeById);
    }

    private <T> boolean areNotEqual(T first, T second) {
        return second != null && !Objects.equals(first, second);
    }

    private Optional<Store> exists(UUID id) {
        var byId = storeRepository.findById(id);
        if (byId.isEmpty()) throw new IllegalStateException("Store by ID : " + id + " does not exists.");
        return byId;
    }
}