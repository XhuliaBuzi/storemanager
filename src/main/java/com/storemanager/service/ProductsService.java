package com.storemanager.service;

import com.storemanager.model.Products;
import com.storemanager.repository.ProductsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
        return exists(id);
    }

    public Products addProduct(Products products) {
        var add = productsRepository.findByName(products.getName());
        if (add.isPresent()) throw new IllegalStateException("Product it is on our list. ");
        return productsRepository.save(products);
    }

    public void deleteProducts(UUID id) {
        exists(id);
        productsRepository.deleteById(id);
    }

    public Products updateProducts(UUID idProduct, Products updateOneProducts) {
        exists(idProduct);
        var products = productsRepository.getById(idProduct);
        final var forUpdateDescription = updateOneProducts.getDescription();
        final var forUpdateName = updateOneProducts.getName();
        if (arNotEqual(products.getName(),forUpdateName)) products.setName(forUpdateName);
        if (arNotEqual(products.getDescription(), forUpdateDescription)) products.setDescription(forUpdateDescription);
        return productsRepository.save(products);
    }

    private <T> boolean arNotEqual(T first, T second) {
        return second != null && !Objects.equals(first, second);
    }

    private Optional<Products> exists(UUID id) {
        var byId = productsRepository.findById(id);
        if (byId.isEmpty()) throw new IllegalStateException("Products by ID : " + id + " does not exists. ");
        return byId;
    }

}
