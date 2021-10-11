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

    public List<Products> GetProducts() {
        return productsRepository.findAll();
    }

    public Optional<Products> GetOneProduct(Long id) {
        return productsRepository.findById(id);
    }

    public void AddProduct(Products products) {
        Optional<Products> add = productsRepository.findById(products.getId());
        if (add.isPresent()) throw new IllegalStateException("Product it is on our list.");
        productsRepository.save(products);
    }

    public void DeleteProducts(Long id) {
        boolean exists = productsRepository.existsById(id);
        if (!exists) throw new IllegalStateException("Products by ID : " + id + " does not exists.");
        productsRepository.deleteById(id);
    }


}
