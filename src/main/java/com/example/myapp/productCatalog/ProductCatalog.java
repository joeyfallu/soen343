package com.example.myapp.productCatalog;

import com.example.myapp.database.ProductMapper;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

    public void modifyProduct(String serialNumber, Product prod){
        System.out.print(prod.toString());
        products.remove(serialNumber);
        prod.setSerialNumber(serialNumber); //making sure to modify the good product
        this.addProduct(prod.getSerialNumber(),prod);


    }
}
