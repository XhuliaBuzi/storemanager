package com.storemanager.service;

import com.storemanager.model.Sale;
import com.storemanager.repository.SaleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
        return exists(id);
    }

    public Sale addSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public void deleteSale(UUID id) {
        exists(id);
        saleRepository.deleteById(id);
    }

    public Sale updateSale(UUID idSale, Sale updateOneSale) {
        exists(idSale);
        var sale = saleRepository.getById(idSale);
        final var forUpdateQuantity = updateOneSale.getQuantity();
        final var forUpdateTotal = updateOneSale.getTotal();
        final var forUpdateDate = updateOneSale.getDate();
        final var forUpdateTime = updateOneSale.getTime();
        if (areNotEqual(sale.getQuantity(), forUpdateQuantity)) sale.setQuantity(forUpdateQuantity);
        if (areNotEqual(sale.getTotal(), forUpdateTotal)) sale.setTotal(forUpdateTotal);
        if (areNotEqual(sale.getDate(), forUpdateDate)) sale.setDate(forUpdateDate);
        if (areNotEqual(sale.getTime(), forUpdateTime)) sale.setTime(forUpdateTime);
        return saleRepository.save(sale);
    }

    private <T> boolean areNotEqual(T first, T second) {
        return second != null && !Objects.equals(first, second);
    }

    private Optional<Sale> exists(UUID id) {
        var byId = saleRepository.findById(id);
        if (byId.isEmpty()) throw new IllegalStateException("Sale by ID : " + id + " does not exists. ");
        return byId;
    }
}
