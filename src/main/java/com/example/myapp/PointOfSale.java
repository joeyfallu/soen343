package com.example.myapp;

import com.example.myapp.cartCatalog.Cart;
import com.example.myapp.cartCatalog.CartCatalog;
import com.example.myapp.productCatalog.ProductCatalog;
import com.example.myapp.purchases.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointOfSale {

    private Store store;
    private CartCatalog cartCatalog;
    @Autowired
    private PurchaseMapper purchaseMapper;

    public PointOfSale(Store store, CartCatalog cartCatalog, PurchaseMapper purchaseMapper) {
        this.store = store;
        this.cartCatalog = cartCatalog;
        this.purchaseMapper = purchaseMapper;
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

    public void endPurchase(int userId){
        ProductCatalog productCatalog = store.getProductCatalog();
        Cart cart = viewCart(userId);
        this.purchaseMapper.insert(cart);

    }

    public Cart viewCart(int userId){
        return cartCatalog.getCart(userId);
    }

}
