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
    public boolean equals(Desktop desktop)
    {
        boolean check = true;
        if (this.getCpuCores()!=desktop.getCpuCores()){check = false;}
        if (!this.getProcessorType().equals(desktop.getProcessorType())){check = false;}
        if (this.getRam()!=desktop.getRam()){check = false;}
        if (this.getHardDriveSize()!=desktop.getHardDriveSize()){check = false;}
        if (!this.getDimensions().equals(desktop.getDimensions())){check = false;}
        if (!this.getBrand().equals(desktop.getBrand())){check=false;}
        if (this.getPrice()!=desktop.getPrice()){check=false;}
        if (this.getWeight()!=desktop.getWeight()){check=false;}
        return check;
    }
}

