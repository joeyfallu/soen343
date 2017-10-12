package com.example.myapp;

import com.example.myapp.actionHandlers.ProductAction;
import com.example.myapp.productCatalog.Product;
import com.example.myapp.productCatalog.ProductCatalog;
import com.example.myapp.userCatalog.UserCatalog;

import java.util.Map;
import java.sql.SQLException;

public class Store {

    private ProductAction productAction;
    private ProductCatalog productCatalog;
    private UserCatalog userCatalog;

    public Store() {
        productCatalog = new ProductCatalog();
        userCatalog = new UserCatalog();
//        newProductAction();
//        productAction.becomeComplete();
//        deleteProduct(4);
    }

    public void newProductAction(){
        this.productAction = new ProductAction(productCatalog);
    }

    public void endAction() {
        this.productAction.becomeComplete();
    }

    public void addNewProduct(int discriminator, String[] values){
        this.productCatalog.addProduct(discriminator,values);
    }
    public void deleteProduct(int id) throws Exception {
        if(productAction.isComplete()){
            productAction = new ProductAction(productCatalog);
            productAction.deleteProduct(id);
        }
    }



    public Map<Integer, Product> viewProductCatalog(){

        return this.productCatalog.getProducts();
    }
}
