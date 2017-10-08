package com.example.myapp;

import com.example.myapp.actionHandlers.ProductAction;
import com.example.myapp.productCatalog.ProductCatalog;
import com.example.myapp.userCatalog.UserCatalog;

public class Store {

    private ProductAction productAction;
    private ProductCatalog productCatalog = new ProductCatalog();
    private UserCatalog userCatalog = new UserCatalog();

    public void newProductAction(){
        this.productAction = new ProductAction(productCatalog);
    }

    public void endAction() {
        this.productAction.becomeComplete();
    }

    public void addNewProduct(int discriminator, String[] values){
        this.productCatalog.addProduct(discriminator,values);
    }


}
