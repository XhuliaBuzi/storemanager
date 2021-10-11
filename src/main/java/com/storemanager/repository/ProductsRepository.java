package com.storemanager.repository;

import com.storemanager.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    @Override
    Optional<Products> findById(Long aLong);
}
