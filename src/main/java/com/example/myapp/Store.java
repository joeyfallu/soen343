package com.example.myapp;

import com.example.myapp.database.ProductMapper;
import com.example.myapp.database.UserMapper;
import com.example.myapp.productCatalog.Product;
import com.example.myapp.productCatalog.ProductCatalog;
import com.example.myapp.purchases.PurchaseMapper;
import com.example.myapp.transactions.Transaction;
import com.example.myapp.userCatalog.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Store {

    private Transaction transaction;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PurchaseMapper purchaseMapper;

    public Store() {
        transaction = new Transaction(Transaction.Type.add);
        transaction.setComplete(true);
    }

    public Store(UserMapper userMapper, ProductMapper productMapper) {
        transaction = new Transaction(Transaction.Type.add);
        transaction.setComplete(true);
        this.userMapper = userMapper;
        this.productMapper = productMapper;
    }

    public ProductCatalog getProductCatalog() {
        return productMapper.getProductCatalog();
    }

    public void addNewProduct(Product product) {
        this.productMapper.insert(product);
    }

    public void deleteProduct(int id) {
        this.productMapper.delete(id);
    }

    public void modifyProduct(int id, Product product) {
        product.setId(id);
        this.productMapper.update(product);
    }

    public void addNewUser(User user) {
        this.userMapper.insert(user);
    }

    public void deleteUser(User user) {
        this.userMapper.delete(user);
    }

    public void initiateTransaction(int userId, Transaction.Type transactionType) {
        if (transaction.isComplete()) {
            transaction.setComplete(false);
            transaction.setUserId(userId);
            transaction.setType(transactionType);
        } else if (!transaction.isComplete()) {
            if (transaction.getUserId() == userId) {
                transaction.setType(transactionType);
                transaction.setUserId(userId);
                transaction.setComplete(false);
            } else {
                System.out.printf("Transaction in progress, please wait");
            }
        }
    }

    public void endTransaction() {
        productMapper.commit();
        userMapper.commit();
        purchaseMapper.commit();

        productMapper.getProductIdentityMap().reset();
        userMapper.getUserIdentityMap().reset();
        purchaseMapper.getPurchasesIdentityMap().reset();

        transaction.setComplete(true);
        transaction.setUserId(-1);
    }

    public ProductMapper getProductMapper() {
        return productMapper;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}
