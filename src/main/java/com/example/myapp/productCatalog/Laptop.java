package com.example.myapp.productCatalog;


public class Laptop extends Computer {
    private String batteryInfo;
    private String operatingSystem;
    private boolean camera;
    private double size;
    private boolean touchScreen;


    public Laptop(int id, String model, double weight, double price, String brand, String processorType, int cpuCores, int ram, int hardDriveSize, double size, String batteryInfo, String operatingSystem, boolean camera, boolean touchScreen){
        super(id, model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize);
        this.camera = camera;
        this.batteryInfo = batteryInfo;
        this.operatingSystem = operatingSystem;
        this.touchScreen = touchScreen;
        this.size = size;

    }

    public Laptop(){
        super();
        this.camera = false;
        this.batteryInfo = null;
        this.operatingSystem = null;
        this.touchScreen = false;
        this.size = 0;

    }

    public String toString(){
        String x;
        x = "id = " + getID() + "\n model = " + getModel() + "\n weight = " + getModel() + "\n price = " + getPrice() + "\n brand = " + getBrand() +
                "\n processorType = " + getProcessorType() + "\n CpuCores = " + getCpuCores() + "\n ram = " + getRam() + "\n hardDriveSize = " + getHardDriveSize() +
                "\n TouchScreen = " + getTouchScreen() + "\n batteryInfo = " +getBatteryInfo() + "\n operatingSystem = " + getOperatingSystem() + "\n camera = " + getCamera() + "\n size = " + getSize();
        return x;
    }

    public boolean equals(Laptop p){
        return (this.getID() == p.getID() && this.getModel().equals(p.getModel()) && this.getWeight() == p.getWeight() && this.getPrice() == p.getPrice() && this.getBrand().equals(p.getBrand()) &&
                this.getProcessorType().equals(p.getProcessorType()) && this.getCpuCores() == p.getCpuCores() && this.getRam() == p.getRam() && this.getHardDriveSize() == p.getHardDriveSize()) &&
                this.getTouchScreen() == (p.getTouchScreen()) && this.getBatteryInfo().equals(p.getBatteryInfo()) && this.getOperatingSystem().equals(p.getOperatingSystem()) && this.getCamera() == (p.getCamera()) && this.size == p.getSize();

    }

    public boolean getTouchScreen(){
        return touchScreen;
    }

    public String getBatteryInfo(){
        return batteryInfo;
    }

    public String getOperatingSystem(){
        return operatingSystem;
    }

    public boolean getCamera(){
        return camera;
    }

    public double getSize(){
        return size;
    }

    public void setTouchScreen(boolean touchScreen){
        this.touchScreen = touchScreen;
    }

    public void setBatteryInfo(String newBI){
        this.batteryInfo = newBI;
    }

    public void setOperatingSystem(String newOS){
        this.operatingSystem = newOS;
    }

    public void setCamera(boolean newC){
        this.camera = newC;
    }

    public void setSize(double newSize){
        this.size = newSize;
    }




}

