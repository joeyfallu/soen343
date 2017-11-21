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

    public PurchaseMapper() {
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

    public void purchase(Purchase purchase) {
        commitType = Transaction.Type.purchase;
        purchasesIdentityMap.insertPurchase(mapCount,purchase);
        mapCount++;
    }

    public void insertPurchaseHistory(String serialNumber, Purchase purchase) {
        purchaseHistory.addPurchase(serialNumber,purchase);
    }

    public void deletePurchaseHistory(String serialNumber) {
        purchaseHistory.deletePurchase(serialNumber);
    }

    public void returnItem(String serialNumber) {
        commitType = Transaction.Type.returnItem;
        Product emptyProduct = new Product("","",0,0,"",0);
        emptyProduct.setSerialNumber(serialNumber);
        Purchase empty = new Purchase(0,"empty",emptyProduct);
        purchasesIdentityMap.insertPurchase(mapCount,empty);
        mapCount++;
    }

    /*public Purchase get(String serialNumber)
    {
        Purchase purchase = purchasesIdentityMap.getPurchaseById(id);

        if(purchase == null)
        {
            try {
                purchase = purchaseTDG.dbGet(serialNumber);
                purchasesIdentityMap.insertPurchaseById(id,purchase);
            }
            catch(Exception e)
            {
                //do nothing
            }
        }
        return purchase;
    }*/

    public Map<String, Purchase> getAll()
    {
        Purchase currentPurchase[];
        try{
            currentPurchase = purchaseTDG.dbGetAll();

            for(int i = 0; i < currentPurchase.length; i++){
                purchaseHistory.addPurchase(currentPurchase[i].getProduct().getSerialNumber(), currentPurchase[i]);
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
