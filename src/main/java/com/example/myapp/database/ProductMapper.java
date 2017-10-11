package com.example.myapp.database;

import com.example.myapp.productCatalog.Product;
import com.example.myapp.productCatalog.ProductCatalog;

import java.util.Map;
import java.sql.SQLException;


public class ProductMapper {

    private ProductIdentityMap productIdentityMap;
    private ProductTDG productTDG;
    private ProductCatalog productCatalog;

    public ProductMapper(ProductCatalog productCatalog) {
        this.productTDG = new ProductTDG();
        this.productCatalog = productCatalog;
        this.productIdentityMap = new ProductIdentityMap();
    }

    public int insert(Product product){
        int id = 0;
        try {
           id = productTDG.dbInsert(product);
        }
        catch(Exception e){
            //do nothing
        }
        productIdentityMap.insertProductById(id, product);
        return id;
    }

    public void update(int id, Product product){
        //TDG here
        try {
            productTDG.dbModify(id, product);
        }
        catch(Exception e){
            //do nothing
        }
        productIdentityMap.updateProductById(id, product);
    }

    public void delete(int id) {
        try {
            productTDG.dbDelete(id);
        }
        catch (Exception e){

        }
        productIdentityMap.deleteProductById(id);
    }

    public Product get(int id){

        Product product = productIdentityMap.getProductById(id);

        if(product == null){
            try {
                product = productTDG.dbGet(id);
                productIdentityMap.insertProductById(id, product);
            }
            catch (Exception e){
                //do nothing
            }
        }

        return product;
    }

    public Map<Integer, Product> getAll(){

        Map<Integer, Product> products = productIdentityMap.getAllProducts();
        Product currentProducts[];

            try{
                currentProducts = productTDG.dbGetAll();

                for(int i = 0; i < currentProducts.length; i++){
                    productIdentityMap.insertProductById(currentProducts[i].getID(), currentProducts[i]);
                }

            }
            catch (Exception e){
                //do nothing
            }

        return products;
    }
}
