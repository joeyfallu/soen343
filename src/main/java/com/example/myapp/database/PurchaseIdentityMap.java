package com.example.myapp.database;


import com.example.myapp.purchases.Purchase;
import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import java.util.HashMap;
import java.util.Map;

public class PurchaseIdentityMap {

    private Map<Integer, Purchase> purchases = new HashMap<>();

    public Purchase getPurchase(int id){
        return purchases.getOrDefault(id, null);
    }

    public Map<Integer, Purchase> getAllPurchases(){ return  purchases;}

    public void insertPurchase(int id, Purchase purchase){
        purchases.put(id,purchase);
    }

    public void updatePurchase(int id, Purchase newPurchase){
        purchases.put(id, newPurchase);
    }

    public void deletePurchase(int id){
        purchases.remove(id);
    }

    public void reset(){purchases = new HashMap<>();}
}
