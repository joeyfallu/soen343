package com.example.myapp;

import com.example.myapp.database.ProductMapper;
import com.example.myapp.database.UserMapper;
import com.example.myapp.productCatalog.Product;
import com.example.myapp.productCatalog.ProductCatalog;
import com.example.myapp.transactions.Transaction;
import com.example.myapp.userCatalog.User;

import java.util.Map;

public class Store {

    private Transaction transaction;
    private ProductMapper productMapper;
    private UserMapper userMapper;

    public Store(UserMapper userMapper, ProductMapper productMapper){

        transaction = new Transaction(Transaction.Type.add);
        transaction.setComplete(true);
        this.userMapper = userMapper;
        this.productMapper = productMapper;

    }

    public ProductCatalog getProductCatalog() {
        return productMapper.getProductCatalog();
    }

    public void addNewProduct(int userId,Product product){
        if (userId!=transaction.getUserId())
        {
            System.out.println("Transaction in progress, please wait and try again");
            return;
        }
        this.productMapper.insert(product);
    }

    public void deleteProduct(int userId,int id){
        if (userId!=transaction.getUserId())
        {
            System.out.println("Transaction in progress, please wait and try again");
            return;
        }
        this.productMapper.delete(id);
    }

    public void modifyProduct(int userId,int id, Product product){
        if (userId!=transaction.getUserId())
        {
            System.out.println("Transaction in progress, please wait and try again");
            return;
        }
        product.setId(id);
        this.productMapper.update(product);
    }


    public void addNewUser(int userId, User user){
        if (userId != transaction.getUserId()) {
            System.out.println("Transaction in progress, please wait and try again");
            return;
        }
        this.userMapper.insert(user);
    }


    public Map<Integer, Product> viewProductCatalog(){
        return this.productMapper.getProductCatalog().getProducts();
    }

    public void initiateTransaction(int userId, Transaction.Type t){

        if(transaction.isComplete())
        {
            transaction.setComplete(false);
            transaction.setUserId(userId);
        }
        else if(!transaction.isComplete())
        {
            if(transaction.getUserId()==userId)
            {
                transaction = new Transaction(t);
                transaction.setUserId(userId);
                transaction.setComplete(false);
            }
            else{
                System.out.printf("transaction in progress, please wait");
            }
        }
    }


    public void endTransaction(int userId)
    {
        if (userId!=transaction.getUserId())
        {
            System.out.println("Transaction in progress, please wait and try again");
            return;
        }
        productMapper.commit();
        userMapper.commit();
        transaction.setComplete(true);
        transaction.setUserId(-1);
    }

    public ProductMapper getProductMapper() {
        return productMapper;
    }

    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public UserMapper getUserMapper(){
        return userMapper;
    }
}
