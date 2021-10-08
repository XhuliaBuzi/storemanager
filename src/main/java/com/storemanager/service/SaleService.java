package com.storemanager.service;

import com.storemanager.model.Sale;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    public List<Sale> GetSale(){
        return List.of(new Sale(1,2,10.4f,java.time.LocalDate.now()));
    }
}
