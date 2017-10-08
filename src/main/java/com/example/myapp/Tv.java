package com.example.myapp;

import com.example.myapp.Product;

public class Tv extends Product.Product {
    private int dimensions;

    public Tv(int id, String model, double weight, double price, String brand, int dimensions){
        super(id, model, weight, price, brand);
        this.dimensions = dimensions;
    }

    public Tv(){
        super();
        this.dimensions = 0;
    }

    public String toString(){
        String x;
        x = "id = " + getID() + " model = " + getModel() + " weight = " + getModel() + " price = " + getPrice() + " brand = " + getBrand() + " dimensions = " + getDimensions();
        return x;
    }

    public boolean equals(Product.Tv p){
        return (this.getID() == p.getID() && this.getModel().equals(p.getModel()) && this.getWeight() == p.getWeight() && this.getPrice() == p.getPrice() && this.getBrand().equals(p.getBrand()) && this.getDimensions() ==(p.getDimensions()));

    }

    public int getDimensions(){
        return dimensions;
    }

    public void setDimensions(int newDimensions){
        this.dimensions = newDimensions;
    }


}
