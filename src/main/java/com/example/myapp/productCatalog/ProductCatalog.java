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


    public Map<Integer, Product> getProducts() {
        return this.products;
    }

    public ProductMapper getProductMapper() {
        return productMapper;
    }

    public void setProducts(Map<Integer, Product> products){
                this.products = products;
    }

    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public void addProduct(Product product){
        System.out.print(product.toString());
        products.put(product.getId(),product);
        //TODO: To be removed
    }

    public void deleteProduct(int id)  {
        products.remove(id);
        productMapper.delete(id);
    }

    public void modifyProduct(int id, Product prod){
        System.out.print(prod.toString());
        deleteProduct(id);
        prod.setId(id); //making sure to modify the good product
        products.put(prod.getId(),prod);

    }
}
