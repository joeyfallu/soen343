package com.example.myapp.productCatalog;

public class Product {
    private int id;
    private String model;
    private double weight;
    private double price;
    private String brand;
    private int discriminator;


    protected Product(int id, String model, double weight, double price, String brand, int discriminator){
        this.id = id;
        this.model = model;
        this.weight = weight;
        this.price = price;
        this.brand = brand;
        this.discriminator = discriminator;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", discriminator=" + discriminator +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(int discriminator) {
        this.discriminator = discriminator;
    }
}
