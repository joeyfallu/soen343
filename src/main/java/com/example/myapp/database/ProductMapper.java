package com.example.myapp.database;

import com.example.myapp.productCatalog.Product;

public class ProductMapper {

    private ProductIdentityMap productIdentityMap = new ProductIdentityMap();
    private ProductTDG productTDG = new ProductTDG();

    public void insert(Product product){
        try {
           product.setID(productTDG.dbInsert(product));
        }
        catch(Exception e){

        }
        productIdentityMap.insertProductById(product.getID(), product);
    }

    public void update(int id, Product product){
        //TDG here
        productIdentityMap.updateProductById(id, product);
    }

    public void delete(int id){
        //TDG here
        productIdentityMap.deleteProductById(id);
    }

    public Product get(int id){

        Product product = productIdentityMap.getProductById(id);
        if(product == null){
            //TDG access stuff here
            return null;
        }
        else
            return product;
    }
}
