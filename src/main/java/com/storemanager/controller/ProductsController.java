package com.storemanager.controller;

import com.storemanager.model.Products;
import com.storemanager.service.ProductsService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/products")
public class ProductsController {
    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public Page<Products> getProducts(@RequestParam(required = false, value = "sort", defaultValue = "name") String sort) {
        return productsService.getProducts(sort);
    }

    @GetMapping(path = "/{id}")
    public Optional<Products> getOneProduct(@PathVariable("id") UUID id) {
        return productsService.getOneProduct(id);
    }

    @PostMapping
    public Products addProducts(@RequestBody Products product) {
        return productsService.addProduct(product);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable("id") UUID id) {
        productsService.deleteProducts(id);
    }
}
