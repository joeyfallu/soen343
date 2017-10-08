package com.example.myapp;

public class Product {
    private int id;
    private String model;
    private double weight;
    private double price;
    private String brand;

    public Product(){
        this.id = 0;
        this.model = null;
        this.weight = 0;
        this.price = 0;
        this.brand = null;
    }

    protected Product(int id, String model, double weight, double price, String brand){
        this.id = id;
        this.model = model;
        this.weight = weight;
        this.price = price;
        this.brand = brand;
    }

    public String toString(){
        String x;
        x = "id = " + getID() + " model = " + getModel() + " weight = " + getModel() + " price = " + getPrice() + " brand = " + getBrand();
        return x;
    }

    public boolean equals(Product p){
        return (id == p.getID() && model.equals(p.getModel()) && weight == p.getWeight() && price == p.getPrice() && brand.equals(p.getBrand()));

    }

    public int getID(){
        return id;
    }

    public String getModel(){
        return model;
    }

    public double getWeight(){
        return weight;
    }

    public double getPrice(){
        return price;
    }

    public String getBrand(){
        return brand;
    }

    public void setID(int newId){
        this.id = newId;
    }

    public void setModel(String newModel){
        this.model = newModel;
    }

    public void setWeight(double newWeight){
        this.weight = newWeight;
    }

    public void setPrice(double newPrice){
        this.price = newPrice;
    }

    public void setBrand(String newBrand){
        this.brand = newBrand;
    }

}
