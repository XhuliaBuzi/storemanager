package com.storemanager.service;

import com.storemanager.model.Sale;
import com.storemanager.repository.SaleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class SaleService {
    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Page<Sale> getSale() {
        Pageable pageable = PageRequest.of(0, 2);
        return saleRepository.findAll(pageable);
    }

    public Optional<Sale> getOneSale(UUID id) {
        exists(id);
        return saleRepository.findById(id);
    }

    public Sale addSale(Sale sale) {
//        Optional<Sale> useID = saleRepository.findById(sale.getId());
//        if (useID.isPresent()) throw new IllegalStateException("Sale " + sale.getId() + " it is taken. ");

        return saleRepository.save(sale);
    }

    public void deleteSale(UUID id) {
        exists(id);
        saleRepository.deleteById(id);
    }

    private void exists(UUID id) {
        boolean existsById = saleRepository.existsById(id);
        if (!existsById) throw new IllegalStateException("Sale by ID : " + id + " does not exists. ");
    }

}
