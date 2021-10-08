package com.storemanager.service;

import com.storemanager.model.Inventory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    public List<Inventory> GetInventory(){
        return List.of(new Inventory(1l,23,2.34f));
    }
}
