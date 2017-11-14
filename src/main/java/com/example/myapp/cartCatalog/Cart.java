package com.example.myapp.cartCatalog;


import java.util.Date;
import java.util.Map;

public class Cart {

    private int userId;
    private int size;
    private Map<Integer, Date> cartProducts;

    public Cart(int userId, int size, Map<Integer, Date> cartProducts) {
        this.userId = userId;
        this.size = size;
        this.cartProducts = cartProducts;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<Integer, Date> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(Map<Integer, Date> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
