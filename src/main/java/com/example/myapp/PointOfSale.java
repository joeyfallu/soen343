package com.example.myapp;

import com.example.myapp.cartCatalog.Cart;
import com.example.myapp.cartCatalog.CartCatalog;
import com.example.myapp.productCatalog.Product;
import com.example.myapp.productCatalog.ProductCatalog;
import com.example.myapp.purchases.Purchase;
import com.example.myapp.purchases.PurchaseHistory;
import com.example.myapp.purchases.PurchaseMapper;
import com.example.myapp.transactions.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

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

    //TODO: For testing purposes
    public Store getStore() {
        return store;
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
        Map<Integer, Product> productCatalog = store.getProductCatalog().getProducts();
        Map<Integer, Date> productsInCart = cartCatalog.purchaseCart(userId);
        Set<Integer> productIdsInCart = productsInCart.keySet();

        for (Integer integer : productIdsInCart) {
            System.out.println(integer);
            System.out.println(productCatalog.get(integer).toString());
            System.out.println(productsInCart.get(integer).toString());
            System.out.println(purchaseMapper.toString());
        }

        store.initiateTransaction(userId, Transaction.Type.purchase);
        for (Integer itemId : productIdsInCart) {
            purchaseMapper.purchase(new Purchase(userId,productsInCart.get(itemId).toString(),productCatalog.get(itemId)));
        }
        store.endTransaction(userId);

        store.initiateTransaction(userId, Transaction.Type.delete);
        for (Integer itemId : productIdsInCart) {
            store.deleteProduct(userId,itemId);
        }
        store.endTransaction(userId);

        cartCatalog.emptyCart(userId);
    }

    public void processReturn(int userId, int itemId){
        Map<Integer, Purchase> purchases = purchaseMapper.getPurchaseHistory().getPurchases();
        Collection<Purchase> values = purchases.values();
        Product productToReturn = null;
        for (Purchase value : values) {
            if(value.getProduct().getId() == itemId) {
                productToReturn = value.getProduct();
            }
        }
        store.initiateTransaction(userId, Transaction.Type.returnItem);
        purchaseMapper.returnItem(itemId);
        store.endTransaction(userId);

        store.initiateTransaction(userId, Transaction.Type.add);
        store.addNewProduct(userId, productToReturn);
        store.endTransaction(userId);
    }

    public Cart viewCart(int userId){
        return cartCatalog.getCart(userId);
    }

    public PurchaseMapper getPurchaseMapper() {
        return purchaseMapper;
    }
}
