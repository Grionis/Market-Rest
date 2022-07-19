package com.test.market.domain.service;

import com.test.market.domain.Purchase;
import com.test.market.persistence.CompraRepository;
import com.test.market.persistence.crud.CompraCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServices {

    @Autowired
    private CompraRepository compraRepository;

    public List<Purchase> getAll(){
        return compraRepository.getAll();
    }

    public Optional<List<Purchase>> getByClient(String clientId){
        return compraRepository.getByClient(clientId);
    }

    public Purchase save(Purchase purchase){
        return compraRepository.save(purchase);

    }

}
