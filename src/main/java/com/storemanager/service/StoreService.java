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

    public List<Store> GetService() {
        return storeRepository.findAll();
    }

    public Optional<Store> GetOneUser(Long id) {
        Exists(id);
        return storeRepository.findById(id);
    }

    public void AddNewUser(Store store) {
        Optional<Store> userById = storeRepository.findById(store.getId());
        if (userById.isPresent()) throw new IllegalStateException("ID taken");
        storeRepository.save(store);
    }

    public void DeleteStore(Long id) {
        Exists(id);
        storeRepository.deleteById(id);
    }

    private void Exists(Long id) {
        boolean exists = storeRepository.existsById(id);
        if (!exists) throw new IllegalStateException("Store by ID : " + id + " does not exists.");
    }

}
