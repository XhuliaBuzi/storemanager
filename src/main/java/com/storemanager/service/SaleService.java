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

    public Optional<Sale> getOneUser(Long id) {
        exists(id);
        return saleRepository.findById(id);
    }

    public Sale addSale(Sale sale) {
        Optional<Sale> useID = saleRepository.findById(sale.getId());
        if (useID.isPresent()) throw new IllegalStateException("Sale " + sale.getId() + " it is taken. ");
        return saleRepository.save(sale);
    }

    public void deleteSale(Long id) {
        exists(id);
        saleRepository.deleteById(id);
    }

    private void exists(Long id) {
        if (!saleRepository.existsById(id)) throw new IllegalStateException("Sale by ID : " + id + " does not exists. ");
    }

}
