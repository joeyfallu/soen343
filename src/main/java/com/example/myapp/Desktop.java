package com.example.myapp;

public class Desktop extends Computer {
    private String dimensions;
    private String batteryInfo;
    private String operatingSystem;
    private String cameraInfo;
    private double size;


    public Desktop(int id, String model, double weight, double price, String brand, String processorType, int cpuCores, int ram, int hardDriveSize, String dimensions){
        super(id, model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize);
        this.dimensions = dimensions;


    }

    public Desktop(){
        super();
        this.dimensions = null;


    }

    public String toString(){
        String x;
        x = "id = " + getID() + "\n model = " + getModel() + "\n weight = " + getModel() + "\n price = " + getPrice() + "\n brand = " + getBrand() +
                "\n processorType = " + getProcessorType() + "\n CpuCores = " + getCpuCores() + "\n ram = " + getRam() + "\n hardDriveSize = " + getHardDriveSize() +
                "\n dimensions = " + getDimensions();
        return x;
    }

    public boolean equals(Tablet p){
        return (this.getID() == p.getID() && this.getModel().equals(p.getModel()) && this.getWeight() == p.getWeight() && this.getPrice() == p.getPrice() && this.getBrand().equals(p.getBrand()) &&
                this.getProcessorType().equals(p.getProcessorType()) && this.getCpuCores() == p.getCpuCores() && this.getRam() == p.getRam() && this.getHardDriveSize() == p.getHardDriveSize()) &&
                this.dimensions.equals(p.getDimensions());

    }

    public String getDimensions(){
        return dimensions;
    }


    public void setDimensions(String newDimensions){
        this.dimensions = newDimensions;
    }






}

