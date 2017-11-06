package com.example.myapp.purchases;


import com.example.myapp.database.PurchaseTDG;
import com.example.myapp.database.PurchaseIdentityMap;
import com.example.myapp.productCatalog.Product;
import com.example.myapp.transactions.Transaction;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PurchaseMapper {

    private PurchaseIdentityMap purchasesIdentityMap;
    private PurchaseTDG purchaseTDG;
    private PurchaseHistory purchaseHistory;
    private Transaction.Type commitType = Transaction.Type.purchase;
    private int mapCount=0;

    public PurchaseMapper()
    {
        this.purchaseTDG = new PurchaseTDG();
        this.purchaseHistory = new PurchaseHistory();
        this.purchasesIdentityMap= new PurchaseIdentityMap();
        getPurchaseHistory().setPurchases(getAll());

    }

    public PurchaseMapper(PurchaseTDG purchaseTDG, PurchaseHistory purchaseHistory, Transaction.Type commitType) {
        this.purchaseTDG = purchaseTDG;
        this.purchaseHistory = purchaseHistory;
        this.commitType = commitType;
    }

    public void purchase(Purchase purchase)
    {
        commitType = Transaction.Type.purchase;
        purchasesIdentityMap.insertPurchaseById(mapCount,purchase);
        mapCount++;
    }

    public void insertPurchaseCatalog(int index,Purchase purchase)
    {
        purchaseHistory.addPurchase(index,purchase);
    }

    public void deletePurchaseCatalog(int id)
    {
        purchaseHistory.deletePurchase(id);
    }

    public void returnItem(int id)
    {
        commitType = Transaction.Type.returnItem;
        Product emptyProduct = new Product(0,"",0,0,"",0);
        emptyProduct.setId(id);
        Purchase empty = new Purchase(0,"empty",emptyProduct);
        purchasesIdentityMap.insertPurchaseById(mapCount,empty);
        mapCount++;
    }

    public Purchase get(int id)
    {
        Purchase purchase = purchasesIdentityMap.getPurchaseById(id);

        if(purchase == null)
        {
            try {
                purchase = purchaseTDG.dbGet(id);
                purchasesIdentityMap.insertPurchaseById(id,purchase);
            }
            catch(Exception e)
            {

            }
        }
        return purchase;
    }

    public Map<Integer, Purchase> getAll()
    {
        Purchase currentPurchase[];
        try{
            currentPurchase = purchaseTDG.dbGetAll();

            for(int i = 0; i < currentPurchase.length; i++){
                purchaseHistory.addPurchase(currentPurchase[i].getProduct().getId(), currentPurchase[i]);
            }

        }
        catch (Exception e){
            //do nothing
        }
        return purchaseHistory.getPurchases();
    }

    public void commit(){mapCount=0;}

    public PurchaseHistory getPurchaseHistory() {
        return purchaseHistory;
    }

    public int getMapCount() {
        return mapCount;
    }

    public PurchaseIdentityMap getPurchasesIdentityMap() {
        return purchasesIdentityMap;
    }

    public PurchaseTDG getPurchaseTDG()
    {
        return purchaseTDG;
    }

    public Transaction.Type getCommitType() {
        return commitType;
    }
}
