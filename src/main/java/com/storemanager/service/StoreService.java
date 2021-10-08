package com.storemanager.service;

import com.storemanager.model.Store;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {



    public List<Store> GetService(){
        return List.of(new Store(1,"Xhulia","Berat,Albania","Une jam Xhulia Buzi dhe po mundohem te mesoj programim"));
    }
}
