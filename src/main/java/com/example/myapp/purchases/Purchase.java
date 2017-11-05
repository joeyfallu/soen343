package com.example.myapp.purchases;

import com.example.myapp.productCatalog.Product;

public class Purchase{
    private int userId;
    private String timeStamp;
    private Product product;

    public Purchase(int userId, String timeStamp, Product product) {
        this.userId = userId;
        this.timeStamp = timeStamp;
        this.product = product;
    }

    public String toString(){
        String str = "";
        str += "User: " + userId + "\n";
        str += timeStamp;
        str += product.toString();
        return str;
    }

    public int getUserId() {
        return userId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public Product getProduct() {
        return product;
    }
}
