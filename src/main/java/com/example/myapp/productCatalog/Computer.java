package com.example.myapp.productCatalog;

public class Computer extends Product {
    private String processorType;
    private int cpuCores;
    private int ram;
    private int hardDriveSize;


    public Computer(String serialNumber, String model, double weight, double price, String brand, String processorType, int cpuCores, int ram, int hardDriveSize, int discrimination){
        super(serialNumber, model, weight, price, brand, discrimination);
        this.processorType = processorType;
        this.cpuCores = cpuCores;
        this.ram = ram;
        this.hardDriveSize = hardDriveSize;
    }

    public String getProcessorType() {
        return processorType;
    }

    public void setProcessorType(String processorType) {
        this.processorType = processorType;
    }

    public int getCpuCores() {
        return cpuCores;
    }

    public void setCpuCores(int cpuCores) {
        this.cpuCores = cpuCores;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getHardDriveSize() {
        return hardDriveSize;
    }

    public void setHardDriveSize(int hardDriveSize) {
        this.hardDriveSize = hardDriveSize;
    }
}
