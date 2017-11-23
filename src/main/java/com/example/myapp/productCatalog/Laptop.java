package com.example.myapp.productCatalog;


public class Laptop extends Computer {
    private String batteryInfo;
    private String operatingSystem;
    private boolean camera;
    private double size;
    private boolean touchScreen;

    public Laptop(String serialNumber, String model, double weight, double price, String brand, String processorType, int cpuCores, int ram, int hardDriveSize,
                  double size, String batteryInfo, String operatingSystem, boolean camera, boolean touchScreen, int discriminator){
        super(serialNumber, model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize, discriminator);
        this.camera = camera;
        this.batteryInfo = batteryInfo;
        this.operatingSystem = operatingSystem;
        this.touchScreen = touchScreen;
        this.size = size;

    }

    public String getBatteryInfo() {
        return batteryInfo;
    }

    public void setBatteryInfo(String batteryInfo) {
        this.batteryInfo = batteryInfo;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public boolean isCamera() {
        return camera;
    }

    public void setCamera(boolean camera) {
        this.camera = camera;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public boolean isTouchScreen() {
        return touchScreen;
    }

    public void setTouchScreen(boolean touchScreen) {
        this.touchScreen = touchScreen;
    }

    public int getTouchScreen() { return touchScreen ? 1 : 0; }

    public int getCamera() { return camera ? 1 : 0; }

    public boolean equals(Laptop laptop)
    {
        boolean check = true;
        if (this.getCpuCores()!=laptop.getCpuCores()){check = false;}
        if (!this.getProcessorType().equals(laptop.getProcessorType())){check = false;}
        if (this.getRam()!=laptop.getRam()){check = false;}
        if (this.getHardDriveSize()!=laptop.getHardDriveSize()){check = false;}
        if (this.getSize()!=laptop.getSize()){check = false;}
        if (!this.getBatteryInfo().equals(laptop.getBatteryInfo())){check = false;}
        if (!this.getOperatingSystem().equals(laptop.getOperatingSystem())){check = false;}
        if (this.isCamera()!=laptop.isCamera()){check = false;}
        if (this.getTouchScreen()!=laptop.getTouchScreen()){check = false;}
        if (!this.getBrand().equals(laptop.getBrand())){check=false;}
        if (this.getPrice()!=laptop.getPrice()){check=false;}
        if (this.getWeight()!=laptop.getWeight()){check=false;}
        return check;
    }
}

