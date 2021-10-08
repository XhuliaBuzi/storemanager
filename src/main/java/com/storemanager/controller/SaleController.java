package com.storemanager.controller;

import com.storemanager.model.Sale;
import com.storemanager.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/sale")
public class SaleController {
    private final SaleService saleService;
@Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }
    @GetMapping
    public List<Sale>GetSale(){
    return saleService.GetSale();
    }
}
