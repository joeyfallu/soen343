package com.example.myapp.productCatalog;


public class Laptop extends Computer {
    private String batteryInfo;
    private String operatingSystem;
    private boolean camera;
    private double size;
    private boolean touchScreen;

    public Laptop(int id, String model, double weight, double price, String brand, String processorType, int cpuCores, int ram, int hardDriveSize,
                  double size, String batteryInfo, String operatingSystem, boolean camera, boolean touchScreen, int discriminator){
        super(id, model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize, discriminator);
        this.camera = camera;
        this.batteryInfo = batteryInfo;
        this.operatingSystem = operatingSystem;
        this.touchScreen = touchScreen;
        this.size = size;

    }

    @Override
    public String toString() {
        return super.toString() + "Laptop{" +
                "batteryInfo='" + batteryInfo + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", camera=" + camera +
                ", size=" + size +
                ", touchScreen=" + touchScreen +
                '}';
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
}

