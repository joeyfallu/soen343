package com.example.myapp.productCatalog;

public class Tv extends Product {
    private String dimensions;

    public Tv(int id, String model, double weight, double price, String brand, String dimensions, int discriminator){
        super(id, model, weight, price, brand, discriminator);
        this.dimensions = dimensions;
    }

    @Override
    public String toString() {
        return super.toString() + "Tv{" +
                "dimensions='" + dimensions + '\'' +
                '}';
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }
}
