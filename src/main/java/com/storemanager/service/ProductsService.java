package com.storemanager.service;

import com.storemanager.model.Products;
import com.storemanager.repository.ProductsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Page<Products> getProducts(String sort) {
        Pageable pageable = PageRequest.of(0, 2, Sort.by(sort));
        return productsRepository.findAll(pageable);
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
        boolean existsById = productsRepository.existsById(id);
        if (!existsById)  throw new IllegalStateException("Products by ID : " + id + " does not exists. ");
    }

}
