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

    public List<Sale> GetSale() {
        return saleRepository.findAll();
    }

    public void AddSale(Sale sale) {
        Optional<Sale> useID = saleRepository.findById(sale.getId());
        if (useID.isPresent()) throw new IllegalStateException("Sale " + sale.getId() + " it is taken.");
        saleRepository.save(sale);
    }

    public void DeleteSale(Long id) {
        boolean exists = saleRepository.existsById(id);
        if (!exists) throw new IllegalStateException("Sale by ID : " + id + " does not exists.");
        saleRepository.deleteById(id);
    }
}
