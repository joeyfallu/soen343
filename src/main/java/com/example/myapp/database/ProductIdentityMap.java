package com.example.myapp.database;


import com.example.myapp.productCatalog.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductIdentityMap {

    private Map<Integer, Product> products = new HashMap<>();

    public Product getProductById(int id){
        return products.getOrDefault(id, null);
    }

    public Map<Integer, Product> getAllProducts(){ return  products;}

    public void insertProductById(int id, Product product){
        products.put(id,product);
    }

    public void updateProductById(int id, Product newProduct){
        products.put(id, newProduct);
    }

    public void deleteProductById(int id){
        products.remove(id);
    }
}
