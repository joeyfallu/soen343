package com.example.myapp.database;

import com.example.myapp.productCatalog.Product;
import com.example.myapp.productCatalog.ProductCatalog;

public class ProductMapper {

    private ProductIdentityMap productIdentityMap;
    private ProductTdg productTdg;
    private ProductCatalog productCatalog;

    public ProductMapper(ProductCatalog productCatalog) {
        this.productTdg = new ProductTdg();
        this.productCatalog = productCatalog;
        this.productIdentityMap = new ProductIdentityMap();
    }

    public int insert(Product product){
        int id = 0;
        try {
           id = productTdg.dbInsert(product);
        }
        catch(Exception e){
            //do nothing
        }
        productIdentityMap.insertProductById(id, product);
        return id;
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
            try {
                product = productTdg.dbGet(id);
                productIdentityMap.insertProductById(id, product);
            }
            catch (Exception e){
                //do nothing
            }
        }

        return product;
    }
}
