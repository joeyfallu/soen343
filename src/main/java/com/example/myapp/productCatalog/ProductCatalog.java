package com.example.myapp.productCatalog;

import com.example.myapp.database.ProductMapper;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductCatalog {

    private Map<Integer, Product> products;


    public ProductCatalog() {

        this.products = new HashMap<>();
    }


    public Map<Integer, Product> getProducts() {
        return this.products;
    }


    public void setProducts(Map<Integer, Product> products){
                this.products = products;
    }


    public void addProduct(int id, Product product){
        products.put(id,product);
        //TODO output to console to be removed after debugging
        //System.out.println("Item has been added:" + products.get(id).toString());
    }

    public void deleteProduct(int id)  {
        products.remove(id);

    }

    public void modifyProduct(int id, Product prod){
        System.out.print(prod.toString());
        products.remove(id);
        prod.setId(id); //making sure to modify the good product
        this.addProduct(prod.getId(),prod);


    }
}
