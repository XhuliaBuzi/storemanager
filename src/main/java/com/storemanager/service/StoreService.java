package com.storemanager.service;

import com.storemanager.model.Store;
import com.storemanager.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> getService() {
        return storeRepository.findAll();
    }

    public Optional<Store> getOneUser(UUID id) {
        exists(id);
        return storeRepository.findById(id);
    }

    public Store addNewUser(Store store) {
        Optional<Store> userById = storeRepository.findByName(store.getName());
        if (userById.isPresent()) throw new IllegalStateException("ID taken");
        return storeRepository.save(store);
    }

    public void deleteStore(UUID id) {
        exists(id);
        storeRepository.deleteById(id);
    }

    private void exists(UUID id) {
        if (!storeRepository.existsById(id))
            throw new IllegalStateException("Store by ID : " + id + " does not exists.");
    }

}
