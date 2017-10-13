package com.example.myapp;

import com.example.myapp.actionHandlers.ProductAction;
import com.example.myapp.database.ProductMapper;
import com.example.myapp.productCatalog.Product;
import com.example.myapp.productCatalog.ProductCatalog;
import com.example.myapp.transactions.Transaction;
import com.example.myapp.userCatalog.UserCatalog;
import com.example.myapp.userCatalog.User;

import java.util.Map;
import java.sql.SQLException;

public class Store {

    private ProductAction productAction;
    private ProductCatalog productCatalog = new ProductCatalog();
    private UserCatalog userCatalog;

    private Transaction transaction = new Transaction();
    private ProductMapper productMapper = new ProductMapper(productCatalog);


    public Store(){
        transaction = new Transaction();
        userCatalog = new UserCatalog();
    }

    public ProductCatalog getProductCatalog() {

        return productCatalog;
    }

    public void setProductCatalog(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    public void newProductAction(){
        this.productAction = new ProductAction(productCatalog);
    }

    public void endAction() {
        this.productAction.becomeComplete();
    }

    public void addNewProduct(Product product){
        this.productMapper.insert(product);
    }

    public void deleteProduct(int id){
        if(productAction.isComplete()){
            productAction = new ProductAction(productCatalog);
//            productAction.deleteProduct(id);
        }
    }

<<<<<<< HEAD
    public void addNewUser(User user){
        this.userCatalog.registerUser(user);
    }



=======
>>>>>>> master
    public Map<Integer, Product> viewProductCatalog(){

        return this.productCatalog.getProducts();
    }

    public void initiateTransaction(int userId){

    }
}
