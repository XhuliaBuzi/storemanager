package com.storemanager.controller;

import com.storemanager.model.Products;
import com.storemanager.service.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public List<Products> getProducts() {
        return productsService.getProducts();
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
