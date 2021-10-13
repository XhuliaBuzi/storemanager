package com.storemanager.controller;

import com.storemanager.model.Sale;
import com.storemanager.service.SaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/find/{id}")
    public Optional<Sale> getOneStore(@PathVariable("id") Long id) {
        return saleService.getOneUser(id);
    }

    @PostMapping
    public Sale addSale(@RequestBody Sale sale) {
        return saleService.addSale(sale);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteSale(@PathVariable("id") Long id) {
        saleService.deleteSale(id);
    }
}
