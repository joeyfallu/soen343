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

    public void addCartItem(int userId, String serialNumber) {
        cartCatalog.addToCart(userId, serialNumber);
    }

    public void removeCartItem(int userId, String serialNumber) {
        cartCatalog.removeFromCart(userId, serialNumber);
    }

    public List<String> endPurchase(int userId) {
        Map<String, Product> productCatalog = store.getProductCatalog().getProducts();
        Map<String, Date> productsInCart = cartCatalog.purchaseCart(userId);
        Set<String> productIdsInCart = productsInCart.keySet();
        List<String> serials = new ArrayList<>();
        for (String serialNumber : productIdsInCart) {
            serials.add(serialNumber);
            purchaseMapper.purchase(new Purchase(userId, productsInCart.get(serialNumber).toString(), productCatalog.get(serialNumber)));
        }
        cartCatalog.emptyCart(userId);
        return serials;
    }

    public void processReturn(int userId, String serialNumber) {
        purchaseMapper.returnItem(serialNumber);
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
