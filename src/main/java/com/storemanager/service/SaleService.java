package com.storemanager.service;

import com.storemanager.model.Sale;
import com.storemanager.repository.SaleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    //The last time that we talk you left me home work to try to se wat was the problem that the scale isn't working in the Postman.
    //the problem it is with line 35,36 now it works but i need throws 2 i need your opinion on that. Should i delete thous or we will try to solve the problem?
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
