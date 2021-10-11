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
    public List<Products> GetProducts() {
        return productsService.GetProducts();
    }

    @GetMapping(path = "/find/{id}")
    public Optional<Products> GetOneProduct(@PathVariable("id") Long id) {
        return productsService.GetOneProduct(id);
    }

    @PostMapping(path = "/add")
    public List<Products> AddProducts(@RequestBody Products product) {
        productsService.AddProduct(product);
        return GetProducts();
    }

    @DeleteMapping(path = "/delete/{id}")
    public List<Products> DeleteProduct(@PathVariable("id") Long id) {
        productsService.DeleteProducts(id);
        return GetProducts();
    }
}
