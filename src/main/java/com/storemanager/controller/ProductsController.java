package com.storemanager.controller;

import com.storemanager.model.Products;
import com.storemanager.service.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/find/{id}")
    public Optional<Products> getOneProduct(@PathVariable("id") Long id) {
        return productsService.getOneProduct(id);
    }

    @PostMapping(path = "/add")
    public Products addProducts(@RequestBody Products product) {
        return productsService.addProduct(product);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productsService.deleteProducts(id);
    }
}
