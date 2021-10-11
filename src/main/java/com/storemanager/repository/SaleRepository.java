package com.storemanager.repository;

import com.storemanager.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Override
    Optional<Sale> findById(Long aLong);
}