package com.storemanager.controller;

import com.storemanager.model.Products;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductsController {

    @GetMapping
    public List<Products> GetProducts(){
        return ;
    }
}
