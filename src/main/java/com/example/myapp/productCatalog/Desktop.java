package com.example.myapp.productCatalog;

public class Desktop extends Computer {
    private String dimensions;

    public Desktop(String serialNumber, String model, double weight, double price, String brand, String dimensions, String processorType, int cpuCores, int ram, int hardDriveSize, int discriminator){
        super(serialNumber, model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize, discriminator);
        this.dimensions = dimensions;


    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }
}

