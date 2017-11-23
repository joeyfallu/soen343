package com.example.myapp;

import com.example.myapp.cartCatalog.Cart;
import com.example.myapp.cartCatalog.CartCatalog;
import com.example.myapp.productCatalog.Product;
import com.example.myapp.purchases.Purchase;
import com.example.myapp.purchases.PurchaseMapper;
import com.example.myapp.transactions.Transaction;
import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;
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
    //@Requires("store.getUserMapper().getUserCatalog().getUserById(userId) != null")
    //@Ensures({"cartCatalog.carts.size() == old(cartCatalog.carts.size() + 1)",
    //          "userID > -1 "})
    public void startPurchase(int userId) {
        //cartCatalog.addCart(userId);
    }
    //@Requires("store.getUserMapper().getUserCatalog().getUserById(userId) != null")
    //@Ensures("cartCatalog.getCart(userId).size() == 0")
    public void cancelPurchase(int userId) {
        cartCatalog.emptyCart(userId);
    }
    //@Requires({"store.getUserMapper().getUserCatalog().getUserById(userId) != null",
    //            "serialNumber != null"})
    //@Ensures("cartCatalog.getCart(userId).getSize() == old(cartCatalog.getCart(userId).getSize() + 1)")
    public void addCartItem(int userId, String serialNumber) {
        cartCatalog.addToCart(userId, serialNumber);
    }
    //@Requires({"store.getUserMapper().getUserCatalog().getUserById(userId) != null",
    //        "serialNumber != null"})
    //@Ensures("cartCatalog.getCart(userId).getSize() == old(cartCatalog.getCart(userId).getSize() - 1)")
    public void removeCartItem(int userId, String serialNumber) {
        cartCatalog.removeFromCart(userId, serialNumber);
    }

    //@Requires("store.getUserMapper().getUserCatalog().getUserById(userId) != null")
    /*@Ensures({"purchaseMapper.getMapCount() == old(purchaseMapper.getMapCount() + 1)",
              "cartCatalog.getCart(userId).size() == 0",
              "old(cartCatalog.getCart(userId).size()) == serials.size()"})*/
    public List<String> endPurchase(int userId) {
        Map<String, Product> productCatalog = store.getProductCatalog().getProducts();
        Map<String, Date> productsInCart = cartCatalog.purchaseCart(userId);
        Set<String> productIdsInCart = productsInCart.keySet();
        List<String> serials = new ArrayList<>();
        for (String serialNumber : productIdsInCart) {
            if(productCatalog.containsKey(serialNumber)) {
                serials.add(serialNumber);
                purchaseMapper.purchase(new Purchase(userId, productsInCart.get(serialNumber).toString(), productCatalog.get(serialNumber)));
            }
        }
        cartCatalog.emptyCart(userId);
        return serials;
    }

    //@Requires("serialNumber != null")
    //@Ensures("purchaseMapper.getMapCount() == old(purchaseMapper.getMapCount() + 1)")
    public void processReturn(String serialNumber) {
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
