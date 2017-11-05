package com.example.myapp.purchases;


        import org.springframework.stereotype.Service;


        import java.util.HashMap;
        import java.util.Map;

@Service
public class PurchaseHistory {

    private Map<Integer, Purchase> purchases;


    public PurchaseHistory() {

        this.purchases = new HashMap<>();
    }


    public Map<Integer, Purchase> getPurchases() {
        return this.purchases;
    }


    public void setPurchases(Map<Integer, Purchase> purchases){
        this.purchases = purchases;
    }


    public void addPurchase(int id, Purchase purchase){
        purchases.put(id,purchase);

    }

    public void deletePurchase(int id)  {
        purchases.remove(id);

    }

    public void modifyPurchase(int id, Purchase purchase){
        purchases.remove(id);
        purchase.getProduct().setId(id);
        this.addPurchase(purchase.getProduct().getId(),purchase);


    }
}
