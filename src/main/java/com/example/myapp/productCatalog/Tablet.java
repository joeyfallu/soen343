package com.example.myapp.productCatalog;

public class Tablet extends Computer {
    private String dimensions;
    private String batteryInfo;
    private String operatingSystem;
    private String cameraInfo;
    private double size;


    public Tablet(int id, String model, double weight, double price, String brand,String dimensions, String processorType, int cpuCores, int ram, int hardDriveSize,
                  double size, String batteryInfo, String operatingSystem, String cameraInfo, int discriminator){
        super(id, model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize, discriminator);
        this.dimensions = dimensions;
        this.batteryInfo = batteryInfo;
        this.operatingSystem = operatingSystem;
        this.cameraInfo = cameraInfo;
        this.size = size;

    }

    public String toString(){
        String x;
        x = "id = " + getID() + "\n model = " + getModel() + "\n weight = " + getModel() + "\n price = " + getPrice() + "\n brand = " + getBrand() +
                "\n processorType = " + getProcessorType() + "\n CpuCores = " + getCpuCores() + "\n ram = " + getRam() + "\n hardDriveSize = " + getHardDriveSize() +
                "\n dimensions = " + getDimensions() + "\n batteryInfo = " +getBatteryInfo() + "\n operatingSystem = " + getOperatingSystem() + "\n cameraInfo = " + getCameraInfo() + "\n size = " + getSize();
        return x;
    }

    public boolean equals(Tablet p){
        return (this.getID() == p.getID() && this.getModel().equals(p.getModel()) && this.getWeight() == p.getWeight() && this.getPrice() == p.getPrice() && this.getBrand().equals(p.getBrand()) &&
                this.getProcessorType().equals(p.getProcessorType()) && this.getCpuCores() == p.getCpuCores() && this.getRam() == p.getRam() && this.getHardDriveSize() == p.getHardDriveSize()) &&
                this.dimensions.equals(p.getDimensions()) && this.getBatteryInfo().equals(p.getBatteryInfo()) && this.getOperatingSystem().equals(p.getOperatingSystem()) && this.getCameraInfo().equals(p.getCameraInfo()) && this.size == p.getSize();

    }

    public String getDimensions(){
        return dimensions;
    }

    public String getBatteryInfo(){
        return batteryInfo;
    }

    public String getOperatingSystem(){
        return operatingSystem;
    }

    public String getCameraInfo(){
        return cameraInfo;
    }

    public double getSize(){
        return size;
    }

    public void setDimensions(String newDimensions){
        this.dimensions = newDimensions;
    }

    public void setBatteryInfo(String newBI){
        this.batteryInfo = newBI;
    }

    public void setOperatingSystem(String newOS){
        this.operatingSystem = newOS;
    }

    public void setCameraInfo(String newCI){
        this.cameraInfo = newCI;
    }

    public void setSize(double newSize){
        this.size = newSize;
    }




}
