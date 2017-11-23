package com.example.myapp.productCatalog;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductCatalog {

    private Map<String, Product> products;

    public ProductCatalog() {
        this.products = new HashMap<>();
    }

    public Map<String, Product> getProducts() {
        return this.products;
    }

    public void setProducts(Map<String, Product> products){
                this.products = products;
    }

    public void addProduct(String serialNumber, Product product){
        products.put(serialNumber,product);
    }

    public void deleteProduct(String serialNumber)  {
        products.remove(serialNumber);
    }

    public void modifyProduct(String serialNumber, Product product){
        products.remove(serialNumber);
        product.setSerialNumber(serialNumber);
        this.addProduct(product.getSerialNumber(),product);
    }
}
