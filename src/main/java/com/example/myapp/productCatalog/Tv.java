package com.example.myapp.productCatalog;

public class Tv extends Product {
    private String dimensions;

    public Tv(int id, String model, double weight, double price, String brand, String dimensions, int discriminator){
        super(id, model, weight, price, brand, discriminator);
        this.dimensions = dimensions;
    }

    public Tv(){
        super();
        this.dimensions = "";
    }

    public String toString(){
        String x;
        x = "id = " + getID() + " model = " + getModel() + " weight = " + getWeight() + " price = " + getPrice() + " brand = " + getBrand() + " dimensions = " + getDimensions();
        return x;
    }

    public boolean equals(Tv p){
        return (this.getID() == p.getID() && this.getModel().equals(p.getModel()) && this.getWeight() == p.getWeight() && this.getPrice() == p.getPrice() && this.getBrand().equals(p.getBrand()) && this.getDimensions() ==(p.getDimensions()));

    }

    public String getDimensions(){
        return dimensions;
    }

    public void setDimensions(String newDimensions){
        this.dimensions = newDimensions;
    }


}
