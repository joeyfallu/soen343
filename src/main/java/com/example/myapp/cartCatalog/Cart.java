package com.example.myapp.cartCatalog;


import java.util.Date;
import java.util.Map;

public class Cart {

    private int userId;
    private int size;
    private Map<String, Date> cartProducts;

    public Cart(int userId, int size, Map<String, Date> cartProducts) {
        this.userId = userId;
        this.size = size;
        this.cartProducts = cartProducts;
    }

    public int getUserId() {
        return userId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<String, Date> getCartProducts() {
        return cartProducts;
    }
}
