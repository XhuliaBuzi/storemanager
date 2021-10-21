package com.storemanager.service;

import com.storemanager.model.Store;
import com.storemanager.repository.StoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        exists(id);
        return storeRepository.findById(id);
    }

    public Store addNewStore(Store store) {
        Optional<Store> userById = storeRepository.findByName(store.getName());
        if (userById.isPresent()) throw new IllegalStateException("ID taken");
        return storeRepository.save(store);
    }

    public void deleteStore(UUID id) {
        exists(id);
        storeRepository.deleteById(id);
    }

    private void exists(UUID id) {
        boolean existsById = storeRepository.existsById(id);
        if (!existsById) throw new IllegalStateException("Store by ID : " + id + " does not exists.");
    }

}
