package com.storemanager.repository;

import com.storemanager.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductsRepository extends JpaRepository<Products, UUID> {
    Optional<Products> findByName(String name);
}
