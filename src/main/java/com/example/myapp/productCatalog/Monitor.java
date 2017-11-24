package com.example.myapp.productCatalog;

public class Monitor extends Product {
    private double size;

    public Monitor(String serialNumber, String model, double weight, double price, String brand, double size, int discriminator){
        super(serialNumber, model, weight, price, brand, discriminator);
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public boolean equals(Monitor monitor)
    {
        boolean check = true;
        if (this.size!=monitor.size){check = false;}
        if (!this.getBrand().equals(monitor.getBrand())){check=false;}
        if (this.getPrice()!=monitor.getPrice()){check=false;}
        if (this.getWeight()!=monitor.getWeight()){check=false;}
        return check;
    }
}
