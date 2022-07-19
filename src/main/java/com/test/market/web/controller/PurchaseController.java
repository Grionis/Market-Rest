package com.test.market.web.controller;

import com.test.market.domain.Purchase;
import com.test.market.domain.service.PurchaseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    PurchaseServices purchaseServices;

    @GetMapping("/Client/all")
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(purchaseServices.getAll(), HttpStatus.OK);
    }

    @GetMapping("/Client/ClientId={ClientId}")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("ClientId") String clientId){
        List<Purchase> purchases = purchaseServices.getByClient(clientId).orElse(null);

        return purchases != null && !purchases.isEmpty() ?
                  new ResponseEntity<>(purchases, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/Client/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase){
        return new ResponseEntity<>(purchaseServices.save(purchase),HttpStatus.CREATED);
    }


}
