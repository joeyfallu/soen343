package com.example.myapp.cartCatalog;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CartCatalog {

    private Map<Integer, Cart> carts;

    public CartCatalog(Map<Integer, Cart> carts) {
        this.carts = carts;
    }

    public void addCart(int userId){
        Cart newCart = new Cart(userId,0,0,new HashMap<>());
        carts.put(newCart.getUserId(),newCart);
    }

    public void emptyCart(int userId) {
        Cart cart = carts.get(userId);
        cart.setSize(0);
        cart.setTotal(0);
        Map<Integer, Date> cartProducts = cart.getCartProducts();
        cartProducts.clear();
    }

    public void addToCart(int userId, int itemId){
        Cart cart = carts.get(userId);
        Map<Integer, Date> cartProducts = cart.getCartProducts();
        cartProducts.put(itemId, new Date());
    }

    public void removeFromCart(int userId, int itemId){
        Cart cart = carts.get(userId);
        Map<Integer, Date> cartProducts = cart.getCartProducts();
        cartProducts.remove(itemId);
    }

    public void purchaseCart(){

    }
}
