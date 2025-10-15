package com.web.DataService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.DataService.domain.Purchase;
import com.web.DataService.repository.PurchaseRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseRepository repo;


    public void savePurchase(Purchase purchase) {
        repo.save(purchase);
    }


    public Iterable<Purchase> findAllPurchases() {
        return repo.findAll();
    }


    public Optional<Purchase> findPurchaseById(long id) {
        return repo.findById(id);
    }

}
