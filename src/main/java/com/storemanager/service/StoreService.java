package com.storemanager.service;

import com.storemanager.model.Store;
import com.storemanager.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> getService() {
        return storeRepository.findAll();
    }

    public void addNewUser(Store store) {
        Optional<Store> userById = storeRepository.findById(store.getId());
        if (userById.isPresent()) throw new IllegalStateException("ID taken");
        storeRepository.save(store);
    }
}
