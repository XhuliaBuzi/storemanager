package com.storemanager.service;

import com.storemanager.model.Products;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    public List<Products> GetProducts(){
        return List.of(new Products(1l,"fasule","Nje bime qe behet byrek"));
    }
}
