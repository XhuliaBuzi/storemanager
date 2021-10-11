package com.storemanager.repository;

import com.storemanager.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    @Override
    Optional<Store> findById(Long id);
}
