package com.storemanager.service;

import com.storemanager.model.Products;
import com.storemanager.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Products> getProducts() {
        return productsRepository.findAll();
    }

    public Optional<Products> getOneProduct(UUID id) {
        exists(id);
        return productsRepository.findById(id);
    }

    public Products addProduct(Products products) {
        Optional<Products> add = productsRepository.findByName(products.getName());
        if (add.isPresent()) throw new IllegalStateException("Product it is on our list. ");
        return productsRepository.save(products);
    }

    public void deleteProducts(UUID id) {
        exists(id);
        productsRepository.deleteById(id);
    }

    private void exists(UUID id) {
        if (!productsRepository.existsById(id))
            throw new IllegalStateException("Products by ID : " + id + " does not exists. ");
    }

}
