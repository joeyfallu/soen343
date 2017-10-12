package com.example.myapp.productCatalog;

import com.example.myapp.database.ProductMapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProductCatalog {

    private Map<Integer, Product> products;
    private ProductMapper productMapper;

    public ProductCatalog() {
        this.productMapper = new ProductMapper(this);
        this.products = new HashMap<>();
    }

    public void addProduct(Product product){
        System.out.print(product.toString());
        products.put(product.getId(),product);
        //TODO: To be removed

    }

    public void deleteProduct(int id) throws Exception {
        products.remove(id);
        productMapper.delete(id);
    }

    public void modifyProduct(int id, String[] values){

    }

    public Map<Integer, Product> getProducts() {

        return productMapper.getAll();

    }

}
