package com.storemanager.service;

import com.storemanager.model.Sale;
import com.storemanager.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> getSale() {
        return saleRepository.findAll();
    }

    public void addSale(Sale sale){
        Optional<Sale> usebyID=saleRepository.findById(sale.getId());
        if(usebyID.isPresent()) throw new IllegalStateException("Sale "+sale.getId()+" it is taken.");
        saleRepository.save(sale);
    }
}
