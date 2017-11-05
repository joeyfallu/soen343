package com.example.myapp.database;



import com.example.myapp.purchases.Purchase;

import java.util.HashMap;
import java.util.Map;

public class PurchaseIdentityMap {

    private Map<Integer, Purchase> purchases = new HashMap<>();

    public Purchase getPurchaseById(int id){
        return purchases.getOrDefault(id, null);
    }

    public Map<Integer, Purchase> getAllPurchases(){ return  purchases;}

    public void insertPurchaseById(int id, Purchase purchase){
        purchases.put(id,purchase);
    }

    public void updatePurchaseById(int id, Purchase newPurchase){
        purchases.put(id, newPurchase);
    }

    public void deletePurchaseById(int id){
        purchases.remove(id);
    }
}
