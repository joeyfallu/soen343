package com.example.myapp;

import com.example.myapp.cartCatalog.Cart;
import com.example.myapp.cartCatalog.CartCatalog;
import com.example.myapp.productCatalog.Product;
import com.example.myapp.purchases.Purchase;
import com.example.myapp.purchases.PurchaseMapper;
import com.example.myapp.transactions.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PointOfSale {

    @Autowired
    private Store store;
    @Autowired
    private PurchaseMapper purchaseMapper;
    private CartCatalog cartCatalog;

    public PointOfSale() {
        this.cartCatalog = new CartCatalog();
    }

    public PointOfSale(Store store, CartCatalog cartCatalog, PurchaseMapper purchaseMapper) {
        this.store = store;
        this.cartCatalog = cartCatalog;
        this.purchaseMapper = purchaseMapper;
    }

    public void startPurchase(int userId) {
        cartCatalog.addCart(userId);
    }

    public void cancelPurchase(int userId) {
        cartCatalog.emptyCart(userId);
    }

    public void addCartItem(int userId, int itemId) {
        cartCatalog.addToCart(userId, itemId);
    }

    public void removeCartItem(int userId, int itemId) {
        cartCatalog.removeFromCart(userId, itemId);
    }

    public List<Integer> endPurchase(int userId) {
        Map<Integer, Product> productCatalog = store.getProductCatalog().getProducts();
        Map<Integer, Date> productsInCart = cartCatalog.purchaseCart(userId);
        Set<Integer> productIdsInCart = productsInCart.keySet();
        List<Integer> ids = new ArrayList<>();
        for (Integer itemId : productIdsInCart) {
            ids.add(itemId);
            purchaseMapper.purchase(new Purchase(userId, productsInCart.get(itemId).toString(), productCatalog.get(itemId)));
        }
        cartCatalog.emptyCart(userId);
        return ids;
    }

    public void processReturn(int userId, int itemId) {
        purchaseMapper.returnItem(itemId);
    }

    public Cart viewCart(int userId) {
        return cartCatalog.getCart(userId);
    }

    public CartCatalog getCartCatalog() {
        return cartCatalog;
    }

    public PurchaseMapper getPurchaseMapper() {
        return purchaseMapper;
    }
}
