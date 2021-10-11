package com.storemanager.service;

import com.storemanager.model.Products;
import com.storemanager.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Products> getProducts() {
        return productsRepository.findAll();
    }

    public void addProduct(Products products) {
        Optional<Products> addbyID = productsRepository.findById(products.getId());
        if (addbyID.isPresent()) throw new IllegalStateException("Product it is on our list.");
        productsRepository.save(products);
    }
}
