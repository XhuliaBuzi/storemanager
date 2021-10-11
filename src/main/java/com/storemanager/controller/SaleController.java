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
    public List<Sale> GetSale() {
        return saleService.GetSale();
    }

    @GetMapping(path = "/find/{id}")
    public Optional<Sale> GetOneStore(@PathVariable("id") Long id) {
        return saleService.GetOneUser(id);
    }

    @PostMapping
    public List<Sale> AddSale(@RequestBody Sale sale) {
        saleService.AddSale(sale);
        return GetSale();
    }

    @DeleteMapping(path = "/delete/{id}")
    public List<Sale> DeleteSale(@PathVariable("id") Long id) {
        saleService.DeleteSale(id);
        return GetSale();
    }
}
