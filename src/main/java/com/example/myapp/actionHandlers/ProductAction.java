package com.example.myapp.actionHandlers;

import com.example.myapp.productCatalog.ProductCatalog;

import java.sql.SQLException;

public class ProductAction extends Action {

    private ProductCatalog productCatalog;

    public ProductAction(ProductCatalog productCatalog) {
        super(false);
        this.productCatalog = productCatalog;
    }


    public void addNewProduct(int discrimintor, String [] values){

    }

    public void deleteProduct(int id) throws Exception {
        productCatalog.deleteProduct(id);
    }

    public void modifyProduct (){

    }
}
