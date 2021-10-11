package com.storemanager.repository;

import com.storemanager.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Override
    Optional<Inventory> findById(Long aLong);
}
