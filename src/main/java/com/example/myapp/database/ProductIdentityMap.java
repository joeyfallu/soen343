package com.example.myapp.database;


import com.example.myapp.productCatalog.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductIdentityMap {

    private Map<Integer, Product> products = new HashMap<>();

    public void reset(){products = new HashMap<>();}

    public Product getProductById(int id){
        return products.getOrDefault(id, null);
    }

    public void insertProduct(int id, Product product){
        products.put(id,product);
    }
}
