package com.example.myapp.database;


import com.example.myapp.productCatalog.*;

import java.sql.*;


public class ProductTDG {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public int dbInsert(Product pro) throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager
                .getConnection("jdbc:mysql://soen343-mysql.cutkgxnrwyh2.us-east-1.rds.amazonaws.com:3306/electronics","soen343team","spiderman");
        statement = connect.createStatement();
        String sql = "";
        if(pro instanceof Tv){
            Tv tv = (Tv)pro;
            sql="INSERT INTO Products (model, weight, price, brand, dimensions,discriminator) VALUES ('"+tv.getModel()+"','"+tv.getWeight()
                    +"','"+tv.getPrice()+"','"+tv.getBrand()+"','"+tv.getDimensions()+"','1')";
        }
        if(pro instanceof Monitor){
            Monitor mn = (Monitor)pro;
            sql="INSERT INTO Products (model, weight, price, brand, size,discriminator) VALUES ('"+mn.getModel()+"','"+mn.getWeight()
                    +"','"+mn.getPrice()+"','"+mn.getBrand()+"','"+mn.getSize()+"','2')";
        }
        if(pro instanceof Tablet){
            Tablet tb = (Tablet)pro;
            sql="INSERT INTO Products (model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize,dimensions, batteryInfo,operatingSystem,cameraInfo,size,discriminator) VALUES ('"+tb.getModel()+
            "','"+tb.getWeight()+"',"+tb.getPrice()+"',"+tb.getBrand()+"',"+tb.getProcessorType()+"',"+tb.getCpuCores()+"',"+tb.getRam()+"',"+tb.getHardDriveSize()+"',"+tb.getDimensions()+"',"+
            tb.getBatteryInfo()+"',"+tb.getOperatingSystem()+"',"+tb.getCameraInfo()+"',"+tb.getSize()+"','3')";
        }
        if(pro instanceof Desktop){
            Desktop dt = (Desktop)pro;
            sql="INSERT INTO Products (model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize,dimensions,discriminator) VALUES ('"+dt.getModel()+
                    "','"+dt.getWeight()+"',"+dt.getPrice()+"',"+dt.getBrand()+"',"+dt.getProcessorType()+"',"+dt.getCpuCores()+"',"+dt.getRam()+"',"+dt.getHardDriveSize()+"',"+dt.getDimensions()+"','4')";
        }
        if(pro instanceof Laptop){
            Laptop lp = (Laptop)pro;
            sql="INSERT INTO Products (model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize,size, batteryInfo,operatingSystem,camera,touchScreen,discriminator) VALUES ('"+lp.getModel()+
                    "','"+lp.getWeight()+"',"+lp.getPrice()+"',"+lp.getBrand()+"',"+lp.getProcessorType()+"',"+lp.getCpuCores()+"',"+lp.getRam()+"',"+lp.getHardDriveSize()+"',"+lp.getSize()+"',"+
                    lp.getBatteryInfo()+"',"+lp.getOperatingSystem()+"',"+lp.getCamera()+"',"+lp.getTouchScreen()+"','5')";
        }
        statement.executeUpdate(sql);
        resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() FROM Products");
        int id=-1;
        while(resultSet.next()){
            id = resultSet.getInt(1);
        }

        return id;

    }

    public String dbGet(int id) throws Exception{
        Product ret= new Product();
        String sql = "SELECT * FROM Products WHERE id='" + id + "'";
        resultSet = statement.executeQuery(sql);
        String result="";
        while(resultSet.next())
        {
            for(int i=1; i<18; i++) {
                result += resultSet.getString(i)+" , ";
            }
            return result;
        }
        return null;
    }



}
