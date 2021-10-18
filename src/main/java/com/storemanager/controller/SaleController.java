package com.storemanager.controller;

import com.storemanager.model.Sale;
import com.storemanager.service.SaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/sale")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public List<Sale> getSale() {
        return saleService.getSale();
    }

    @GetMapping(path = "/{id}")
    public Optional<Sale> getOneStore(@PathVariable("id") UUID id) {
        return saleService.getOneUser(id);
    }

    @PostMapping
    public Sale addSale(@RequestBody Sale sale) {
        return saleService.addSale(sale);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteSale(@PathVariable("id") UUID id) {
        saleService.deleteSale(id);
    }
}
