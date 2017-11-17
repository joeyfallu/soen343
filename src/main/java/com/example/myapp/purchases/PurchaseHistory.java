package com.example.myapp.purchases;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

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

    public void deletePurchase(String serialNumber)  {
        purchases.remove(serialNumber);
    }
}
