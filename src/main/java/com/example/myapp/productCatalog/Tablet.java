package com.example.myapp.productCatalog;

public class Tablet extends Computer {
    private String dimensions;
    private String batteryInfo;
    private String operatingSystem;
    private String cameraInfo;
    private double size;


    public Tablet(String serialNumber, String model, double weight, double price, String brand,String dimensions, String processorType, int cpuCores, int ram, int hardDriveSize,
                  double size, String batteryInfo, String operatingSystem, String cameraInfo, int discriminator){
        super(serialNumber, model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize, discriminator);
        this.dimensions = dimensions;
        this.batteryInfo = batteryInfo;
        this.operatingSystem = operatingSystem;
        this.cameraInfo = cameraInfo;
        this.size = size;

    }

    @Override
    public String toString() {
        return super.toString() + "Tablet{" +
                "dimensions='" + dimensions + '\'' +
                ", batteryInfo='" + batteryInfo + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", cameraInfo='" + cameraInfo + '\'' +
                ", size=" + size +
                '}';
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
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

    public String getCameraInfo() {
        return cameraInfo;
    }

    public void setCameraInfo(String cameraInfo) {
        this.cameraInfo = cameraInfo;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
