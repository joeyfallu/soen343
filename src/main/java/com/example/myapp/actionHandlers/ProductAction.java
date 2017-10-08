package com.example.myapp.actionHandlers;

import com.example.myapp.productCatalog.ProductCatalog;

public class ProductAction extends Action {

    private ProductCatalog productCatalog;

    public ProductAction(ProductCatalog productCatalog) {
        super(false);
        this.productCatalog = productCatalog;
    }

    public void addNewProduct(int discrimintor, String [] values){

    }

    public void deleteProduct(){

    }

    public void modifyProduct (){

    }
}
