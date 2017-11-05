package com.example.myapp.purchases;

import com.example.myapp.cartCatalog.Cart;
import com.example.myapp.database.PurchaseTDG;
import com.example.myapp.transactions.Transaction;
import org.springframework.stereotype.Service;

@Service
public class PurchaseMapper {

    private PurchaseTDG purchaseTDG;
    private PurchaseHistory purchaseHistory;
    private int mapCount;
    private Transaction.Type commitType;

    public PurchaseMapper(PurchaseTDG purchaseTDG, PurchaseHistory purchaseHistory, int mapCount, Transaction.Type commitType) {
        this.purchaseTDG = purchaseTDG;
        this.purchaseHistory = purchaseHistory;
        this.mapCount = mapCount;
        this.commitType = commitType;
    }

    public void insert(Cart cart){

    }

}
