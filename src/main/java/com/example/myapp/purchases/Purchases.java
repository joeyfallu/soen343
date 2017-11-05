package com.example.myapp.purchases;

import com.example.myapp.productCatalog.Product;

import java.util.Iterator;
import java.util.Map;

public class Purchases {
    private int userID;
    private Map<Product, String> purchasedProducts;

    public Purchases(int userID, Map<Product, String> purchasedProducts ) {
        this.userID = userID;
        this.purchasedProducts = purchasedProducts;
    }

    public String toString(){
        String str = "";
        str += "User: " + userID + "\n";
        Iterator it =purchasedProducts.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry entry = (Map.Entry)it.next();
            str += entry.getKey().toString();
            str += "\n";
        }
        return str;
    }

    public int getUserID() {
        return userID;
    }

    public Map<Product, String> getPurchasedProducts() {
        return purchasedProducts;
    }
}
