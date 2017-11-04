package com.example.myapp.purchases;

import com.example.myapp.productCatalog.Product;

import java.util.Map;

public class Purchases {
    private int userID;
    private Map<Product, String> purchasedProducts;


    public Purchases(int userID, Map<Product, String> purchasedProducts ) {
        this.userID = userID;
        this.purchasedProducts = purchasedProducts;
    }
}
