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

    public void addProduct(int discriminator, String[] values){
        Product product = null;
        if(discriminator == 1) {
            product = new Tv(0, values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]), values[3], values[4], Integer.parseInt(values[17]));
        } else if (discriminator == 2){
            product = new Monitor(0, values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]), values[3], Integer.parseInt(values[4]), Integer.parseInt(values[17]));
        } else if (discriminator == 3){
            product = new Tablet(0, values[0], Double.parseDouble(values[1]),Double.parseDouble(values[2]),values[3],values[4],values[5],Integer.parseInt(values[6]),Integer.parseInt(values[7]),Integer.parseInt(values[8]),
                    Double.parseDouble(values[9]),values[10],values[11],values[12],Integer.parseInt(values[17]));
        } else if (discriminator == 4){
            product = new Desktop(0, values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]), values[3], values[4], values[5], Integer.parseInt(values[6]),Integer.parseInt(values[7]),Integer.parseInt(values[8]), Integer.parseInt(values[17]));
        } else if (discriminator == 5){
            product = new Laptop(0, values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]), values[3],values[4],Integer.parseInt(values[5]),Integer.parseInt(values[6]),Integer.parseInt(values[7]),
                    Double.parseDouble(values[8]), values[9], values[10], Boolean.parseBoolean(values[11]), Boolean.parseBoolean(values[12]), Integer.parseInt(values[17]));
        }
        int id = productMapper.insert(product);
        products.put(id,product);
    }

    public void deleteProduct(int id) throws Exception {
        productMapper.delete(id);
    }

    public void modifyProduct(int id, String[] values){

    }

    public Map<Integer, Product> getProducts() {

        return productMapper.getAll();

    }

}
