package com.example.myapp.productCatalog;

public class Monitor extends Product {
    private int size;

    public Monitor(int id, String model, double weight, double price, String brand, int size){
        super(id, model, weight, price, brand);
        this.size = size;
    }

    public Monitor(){
        super();
        this.size = 0;
    }

    public String toString(){
        String x;
        x = "id = " + getID() + " model = " + getModel() + " weight = " + getModel() + " price = " + getPrice() + " brand = " + getBrand() + " dimensions = " + getSize();
        return x;
    }

    public boolean equals(Monitor p){
        return (this.getID() == p.getID() && this.getModel().equals(p.getModel()) && this.getWeight() == p.getWeight() && this.getPrice() == p.getPrice() && this.getBrand().equals(p.getBrand()) && this.getSize() == (p.getSize()));

    }

    public int getSize(){
        return size;
    }

    public void setSize(int newSize){
        this.size = newSize;
    }


}
