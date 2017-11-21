package com.example.myapp.purchases;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

@Service
public class PurchaseHistory {

    private Map<String, Purchase> purchases;

    public PurchaseHistory() {
        this.purchases = new HashMap<>();
    }

    public Map<String, Purchase> getPurchases() {
        return this.purchases;
    }

    public void setPurchases(Map<String, Purchase> purchases){
        this.purchases = purchases;
    }

    public void addPurchase(String serialNumber, Purchase purchase){
        purchases.put(serialNumber,purchase);
    }

    @Requires("true")
    @Ensures("purchases.size() == old (purchases.size() - 1)")
    public void deletePurchase(String serialNumber)  {
        purchases.remove(serialNumber);
    }
}
