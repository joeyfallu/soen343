package com.example.myapp.productCatalog;

public class Monitor extends Product {
    private int size;

    public Monitor(String serialNumber, String model, double weight, double price, String brand, int size, int discriminator){
        super(serialNumber, model, weight, price, brand, discriminator);
        this.size = size;
    }

    @Override
    public String toString() {
        return super.toString() + "Monitor{" +
                "size=" + size +
                '}';
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
