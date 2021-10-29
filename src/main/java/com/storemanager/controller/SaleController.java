package com.storemanager.controller;

import com.storemanager.model.Sale;
import com.storemanager.service.SaleService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
    public Page<Sale> getSale() {
        return saleService.getSale();
    }

    @GetMapping(path = "/{id}")
    public Optional<Sale> getOneSale(@PathVariable("id") UUID id) {
        return saleService.getOneSale(id);
    }

    @PostMapping
    public Sale addSale(@RequestBody Sale sale) {
        return saleService.addSale(sale);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteSale(@PathVariable("id") UUID id) {
        saleService.deleteSale(id);
    }

    @PatchMapping(path = "/{idSale}")
    public Sale updateSale(@PathVariable("idSale") UUID idSale, @RequestBody Sale sale) {
        return saleService.updateSale(idSale, sale);
    }
}
