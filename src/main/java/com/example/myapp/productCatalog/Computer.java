package com.example.myapp.productCatalog;

public class Computer extends Product {
    private String processorType;
    private int cpuCores;
    private int ram;
    private int hardDriveSize;


    public Computer(int id, String model, double weight, double price, String brand, String processorType, int cpuCores, int ram, int hardDriveSize, int discrimination){
        super(id, model, weight, price, brand, discrimination);
        this.processorType = processorType;
        this.cpuCores = cpuCores;
        this.ram = ram;
        this.hardDriveSize = hardDriveSize;
    }

    public Computer(){
        super();
        this.processorType = null;
        this.cpuCores = 0;
        this.ram = 0;
        this.hardDriveSize = 0;
    }

    public String toString(){
        String x;
        x = "id = " + getID() + " model = " + getModel() + " weight = " + getWeight() + " price = " + getPrice() + " brand = " + getBrand() + " processorType = " + getProcessorType() + " CpuCores = " + getCpuCores() + " ram = " + getRam() + " hardDriveSize = " + getHardDriveSize();
        return x;
    }

    public boolean equals(Computer p){
        return (this.getID() == p.getID() && this.getModel().equals(p.getModel()) && this.getWeight() == p.getWeight() && this.getPrice() == p.getPrice() && this.getBrand().equals(p.getBrand()) && this.getProcessorType().equals(p.getProcessorType()) && this.getCpuCores() == p.getCpuCores() && this.getRam() == p.getRam() && this.getHardDriveSize() == p.getHardDriveSize());

    }

    public String getProcessorType(){
        return processorType;
    }

    public int getCpuCores(){
        return cpuCores;
    }

    public int getRam(){
        return ram;
    }

    public int getHardDriveSize(){
        return hardDriveSize;
    }



    public void setProcessorType(String newProcessorType){
        this.processorType = newProcessorType;
    }

    public void setCpuCores(int newCpuCores){
        this.cpuCores = newCpuCores;
    }

    public void setRam(int newRam){
        this.ram = newRam;
    }

    public void setHardDriveSize(int newHDS){
        this.hardDriveSize = newHDS;
    }








}
