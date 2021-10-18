package com.storemanager.repository;

import com.storemanager.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface SaleRepository extends JpaRepository<Sale, UUID> {
}
