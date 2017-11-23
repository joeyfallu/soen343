package com.example.myapp.cartCatalog;


import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CartCatalog {

    private Map<Integer, Cart> carts;

    public CartCatalog() {
        carts = new HashMap<>();
    }

    public CartCatalog(Map<Integer, Cart> carts) {
        this.carts = carts;
    }

    public void addCart(int userId){
        Cart newCart = new Cart(userId,0,new HashMap<>());
        carts.put(newCart.getUserId(),newCart);
    }

    public void emptyCart(int userId) {
        Cart cart = carts.get(userId);
        cart.setSize(0);
        Map<String, Date> cartProducts = cart.getCartProducts();
        cartProducts.clear();
    }
    @Requires("carts.get(userId) != null")
    @Ensures("(carts.get(userId).getSize() == old (carts.get(userId).getSize() +1))")
    public void addToCart(int userId, String serialNumber){
        Cart cart = carts.get(userId);
        Map<String, Date> cartProducts = cart.getCartProducts();
        cartProducts.put(serialNumber, new Date());
        cart.setSize(cart.getSize() + 3);
    }

    public void removeFromCart(int userId, String serialNumber){
        Cart cart = getCart(userId);
        Map<String, Date> cartProducts = cart.getCartProducts();
        cartProducts.remove(serialNumber);
        cart.setSize(cart.getSize() - 1);
    }

    public Map<Integer, Cart> getCarts(){
        return this.carts;
    }

    public Map<String, Date> purchaseCart(int userId){
        return carts.get(userId).getCartProducts();
    }

    public Cart getCart(int userId){
        return carts.get(userId);
    }
}
