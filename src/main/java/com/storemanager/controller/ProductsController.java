package com.storemanager.controller;

import com.storemanager.model.Products;
import com.storemanager.service.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductsController {
    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public List<Products> GetProducts() {
        return productsService.GetProducts();
    }

    @PostMapping(path = "/add")
    public void AddProducts(@RequestBody Products product) {
        productsService.AddProduct(product);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void DeleteProduct(@PathVariable("id") Long id) {
        productsService.DeleteProducts(id);
    }
}
