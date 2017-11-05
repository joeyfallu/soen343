package com.example.myapp;

import com.example.myapp.cartCatalog.CartCatalog;

public class PointOfSale {

    private Store store;
    private CartCatalog cartCatalog;

    public PointOfSale(Store store, CartCatalog cartCatalog) {
        this.store = store;
        this.cartCatalog = cartCatalog;
    }

    public void startPurchase(int userId){
        cartCatalog.addCart(userId);
    }

    public void cancelPurchase(int userId){
        cartCatalog.emptyCart(userId);
    }

    public void addCartItem(int userId, int itemId){
        cartCatalog.addToCart(userId,itemId);
    }

}
