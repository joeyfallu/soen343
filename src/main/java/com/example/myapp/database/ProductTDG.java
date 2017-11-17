package com.example.myapp.database;


import com.example.myapp.productCatalog.*;
//import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.sql.*;


public class ProductTDG {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    private void dbConnect() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager
                .getConnection("jdbc:mysql://soen343-mysql.cutkgxnrwyh2.us-east-1.rds.amazonaws.com:3306/electronics","soen343team","spiderman");
        statement = connect.createStatement();
    }

    public void dbInsert(Product pro) throws Exception
    {

        dbConnect();
        String sql = "";
        if(pro instanceof Monitor){
            Monitor mn = (Monitor)pro;
            sql="INSERT INTO Products (serialNumber, model, weight, price, brand, size,discriminator) VALUES ('"+mn.getSerialNumber()+"','"+mn.getModel()+"','"+mn.getWeight()
                    +"','"+mn.getPrice()+"','"+mn.getBrand()+"','"+mn.getSize()+"','2')";
        }
        else if(pro instanceof Tablet){
            Tablet tb = (Tablet)pro;
            sql="INSERT INTO Products (serialNumber, model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize,dimensions, batteryInfo,operatingSystem,cameraInfo,size,discriminator) VALUES ('"+tb.getSerialNumber()+"','"+tb.getModel()+
            "','"+tb.getWeight()+"','"+tb.getPrice()+"','"+tb.getBrand()+"','"+tb.getProcessorType()+"','"+tb.getCpuCores()+"','"+tb.getRam()+"','"+tb.getHardDriveSize()+"','"+tb.getDimensions()+"','"+
            tb.getBatteryInfo()+"','"+tb.getOperatingSystem()+"','"+tb.getCameraInfo()+"','"+tb.getSize()+"','3')";
        }
        else if(pro instanceof Desktop){
            Desktop dt = (Desktop)pro;
            sql="INSERT INTO Products (serialNumber, model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize,dimensions,discriminator) VALUES ('"+dt.getSerialNumber()+"','"+dt.getModel()+
                    "','"+dt.getWeight()+"','"+dt.getPrice()+"','"+dt.getBrand()+"','"+dt.getProcessorType()+"','"+dt.getCpuCores()+"','"+dt.getRam()+"','"+dt.getHardDriveSize()+"','"+dt.getDimensions()+"','4')";
        }
        else if(pro instanceof Laptop){
            Laptop lp = (Laptop)pro;
            sql="INSERT INTO Products (serialNumber, model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize,size, batteryInfo,operatingSystem,camera,touchScreen,discriminator) VALUES ('"+lp.getSerialNumber()+"','"+lp.getModel()+
                    "','"+lp.getWeight()+"','"+lp.getPrice()+"','"+lp.getBrand()+"','"+lp.getProcessorType()+"','"+lp.getCpuCores()+"','"+lp.getRam()+"','"+lp.getHardDriveSize()+"','"+lp.getSize()+"','"+
                    lp.getBatteryInfo()+"','"+lp.getOperatingSystem()+"','"+lp.getCamera()+"','"+lp.getTouchScreen()+"','5')";
        }
        statement.executeUpdate(sql);
        resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() FROM Products");
        /*int id=-1;
        while(resultSet.next()){
            id = resultSet.getInt(1);
        }

        return id;*/

    }

    /*public void dbInsert(Product pro, int id) throws Exception
    {

        dbConnect();
        String sql = "";
        if(pro instanceof Monitor){
            Monitor mn = (Monitor)pro;
            sql="INSERT INTO Products (id, model, weight, price, brand, size,discriminator) VALUES ('"+id+"','"+mn.getModel()+"','"+mn.getWeight()
                    +"','"+mn.getPrice()+"','"+mn.getBrand()+"','"+mn.getSize()+"','2')";
        }
        else if(pro instanceof Tablet){
            Tablet tb = (Tablet)pro;
            sql="INSERT INTO Products (id, model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize,dimensions, batteryInfo,operatingSystem,cameraInfo,size,discriminator) VALUES ('"+id+"','"+tb.getModel()+
                    "','"+tb.getWeight()+"','"+tb.getPrice()+"','"+tb.getBrand()+"','"+tb.getProcessorType()+"','"+tb.getCpuCores()+"','"+tb.getRam()+"','"+tb.getHardDriveSize()+"','"+tb.getDimensions()+"','"+
                    tb.getBatteryInfo()+"','"+tb.getOperatingSystem()+"','"+tb.getCameraInfo()+"','"+tb.getSize()+"','3')";
        }
        else if(pro instanceof Desktop){
            Desktop dt = (Desktop)pro;
            sql="INSERT INTO Products (id, model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize,dimensions,discriminator) VALUES ('"+id+"','"+dt.getModel()+
                    "','"+dt.getWeight()+"','"+dt.getPrice()+"','"+dt.getBrand()+"','"+dt.getProcessorType()+"','"+dt.getCpuCores()+"','"+dt.getRam()+"','"+dt.getHardDriveSize()+"','"+dt.getDimensions()+"','4')";
        }
        else if(pro instanceof Laptop){
            Laptop lp = (Laptop)pro;
            sql="INSERT INTO Products (id, model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize,size, batteryInfo,operatingSystem,camera,touchScreen,discriminator) VALUES ('"+id+"','"+lp.getModel()+
                    "','"+lp.getWeight()+"','"+lp.getPrice()+"','"+lp.getBrand()+"','"+lp.getProcessorType()+"','"+lp.getCpuCores()+"','"+lp.getRam()+"','"+lp.getHardDriveSize()+"','"+lp.getSize()+"','"+
                    lp.getBatteryInfo()+"','"+lp.getOperatingSystem()+"','"+lp.getCamera()+"','"+lp.getTouchScreen()+"','5')";
        }
        statement.executeUpdate(sql);


    }*/



    public Product dbGet(String serialNumber) throws Exception{
        dbConnect();
        String sql = "SELECT * FROM Products WHERE serialNumber='" + serialNumber + "'";
        resultSet = statement.executeQuery(sql);
        String result[]= new String[18];


        while(resultSet.next())
        {
            for(int i=1; i<18; i++) {
                result[i] = resultSet.getString(i);
            }

        }
        if(Integer.parseInt(result[17])==2)
        {
            //1/2/3/4/5/11
           Monitor mn = new Monitor(result[1],result[2],Double.parseDouble(result[3]),Double.parseDouble(result[4]),result[5],Integer.parseInt(result[11]), Integer.parseInt(result[17]));
            return mn;
        }
        if(Integer.parseInt(result[17])==3)
        {
            //1/2/3/4/5/6/7/8/9/10/11/12/13/14/15/
            Tablet tl= new Tablet(result[1],result[2],Double.parseDouble(result[3]),Double.parseDouble(result[4]),result[5],result[6],result[7],
                    Integer.parseInt(result[8]),Integer.parseInt(result[9]),Integer.parseInt(result[10]),Double.parseDouble(result[11]),result[12],result[13],result[14], Integer.parseInt(result[17]));
            return tl;
        }
        if(Integer.parseInt(result[17])==4)
        {
            //1/2/3/4/5/6/7/8/9/10/
            Desktop dt = new Desktop(result[1],result[2],Double.parseDouble(result[3]),Double.parseDouble(result[4]),result[5],result[6],result[7],
                    Integer.parseInt(result[8]),Integer.parseInt(result[9]),Integer.parseInt(result[10]), Integer.parseInt(result[17]));
            return dt;
        }
        if(Integer.parseInt(result[17])==5)
        {
            //1/2/3/4/5/7/8/9/10/11/12/13/15/16
            Laptop lp = new Laptop(result[1],result[2],Double.parseDouble(result[3]),Double.parseDouble(result[4]),result[5],result[7],
                    Integer.parseInt(result[8]),Integer.parseInt(result[9]),Integer.parseInt(result[10]),Double.parseDouble(result[11]),result[12],result[13],
                    Boolean.parseBoolean(result[15]),Boolean.parseBoolean(result[16]), Integer.parseInt(result[17]));
            return lp;
        }
        return null;
    }
    public void dbDelete(String serialNumber) throws Exception {
        dbConnect();
        String sql = "DELETE FROM Products WHERE serialNumber = '" + serialNumber + "'";
        statement.executeUpdate(sql);
    }

    public void dbModify(String serialNumber, Product pro) throws Exception {

        dbConnect();
        String sql = "";
        if(pro instanceof Monitor){
            Monitor mn = (Monitor)pro;
            sql="UPDATE `electronics`.`Products`\n" +
                    "SET\n" +
                    "`model` = '"+mn.getModel()+"',\n" +
                    "`weight` = '"+mn.getWeight()+"',\n" +
                    "`price` = '"+mn.getPrice()+"',\n" +
                    "`brand` = '"+mn.getBrand()+"',\n" +
                    "`size` = '"+mn.getSize()+"',\n" +
                    "`discriminator` = '2'\n" +
                    "WHERE `serialNumber` = "+ serialNumber +";\n";
        }
        if(pro instanceof Tablet){
            Tablet tb = (Tablet)pro;
            sql="UPDATE `electronics`.`Products`\n" +
                    "SET\n" +
                    "`model` = '"+tb.getModel()+"',\n" +
                    "`weight` = '"+tb.getWeight()+"',\n" +
                    "`price` = '"+tb.getPrice()+"',\n" +
                    "`brand` = '"+tb.getBrand()+"',\n" +
                    "`dimensions` = '"+tb.getDimensions()+"',\n" +
                    "`processorType` = '"+tb.getProcessorType()+"',\n" +
                    "`cpuCores` = '"+tb.getCpuCores()+"',\n" +
                    "`ram` = '"+tb.getRam()+"',\n" +
                    "`hardDriveSize` = '"+tb.getHardDriveSize()+"',\n" +
                    "`size` = '"+tb.getSize()+"',\n" +
                    "`batteryInfo` = '"+tb.getBatteryInfo()+"',\n" +
                    "`operatingSystem` = '"+tb.getOperatingSystem()+"',\n" +
                    "`cameraInfo` = '"+tb.getCameraInfo()+"',\n" +
                    "`discriminator` = '3'\n" +
                    "WHERE `serialNumber` = "+ serialNumber +";\n";
        }
        if(pro instanceof Desktop){
            Desktop dt = (Desktop)pro;
            sql="UPDATE `electronics`.`Products`\n" +
                    "SET\n" +
                    "`model` = '"+dt.getModel()+"',\n" +
                    "`weight` = '"+dt.getWeight()+"',\n" +
                    "`price` = '"+dt.getPrice()+"',\n" +
                    "`brand` = '"+dt.getBrand()+"',\n" +
                    "`dimensions` = '"+dt.getDimensions()+"',\n" +
                    "`processorType` = '"+dt.getProcessorType()+"',\n" +
                    "`cpuCores` = '"+dt.getCpuCores()+"',\n" +
                    "`ram` = '"+dt.getRam()+"',\n" +
                    "`hardDriveSize` = '"+dt.getHardDriveSize()+"',\n" +
                    "`discriminator` = '4'\n" +
                    "WHERE `serialNumber` = "+ serialNumber +";\n";
        }
        if(pro instanceof Laptop){
            Laptop lp = (Laptop)pro;
            sql="UPDATE `electronics`.`Products`\n" +
                    "SET\n" +
                    "`model` = '"+lp.getModel()+"',\n" +
                    "`weight` = '"+lp.getWeight()+"',\n" +
                    "`price` = '"+lp.getPrice()+"',\n" +
                    "`brand` = '"+lp.getBrand()+"',\n" +
                    "`processorType` = '"+lp.getProcessorType()+"',\n" +
                    "`cpuCores` = '"+lp.getCpuCores()+"',\n" +
                    "`ram` = '"+lp.getRam()+"',\n" +
                    "`hardDriveSize` = '"+lp.getHardDriveSize()+"',\n" +
                    "`size` = '"+lp.getSize()+"',\n" +
                    "`batteryInfo` = '"+lp.getBatteryInfo()+"',\n" +
                    "`operatingSystem` = '"+lp.getOperatingSystem()+"',\n" +
                    "`cameraInfo` = '"+lp.getCamera()+"',\n" +
                    "`touchScreen` = '"+lp.getTouchScreen()+"',\n" +
                    "`discriminator` = '5'\n" +
                    "WHERE `serialNumber` = "+ serialNumber +";\n";
        }

        statement.executeUpdate(sql);
       // statement.executeQuery(sql);
    }

    public Product[] dbGetAll() throws Exception{
        dbConnect();
        String sql = "SELECT * FROM Products";
        int numberOfProducts = 0;
        int currentProductNum = 0;

        resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            numberOfProducts++;
        }

        Product products[] = new Product[numberOfProducts];
        String result[] = new String[18];

        resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            for (int i = 1; i < 18; i++) {
                result[i] = resultSet.getString(i);
            }

            if (Integer.parseInt(result[17]) == 2) {
                //1/2/3/4/5/11
                Monitor mn = new Monitor(result[1], result[2], Double.parseDouble(result[3]), Double.parseDouble(result[4]), result[5], Integer.parseInt(result[11]), Integer.parseInt(result[17]));
                products[currentProductNum] = mn;
                currentProductNum++;
            }
            if (Integer.parseInt(result[17]) == 3) {
                //1/2/3/4/5/6/7/8/9/10/11/12/13/14/15/
                Tablet tl = new Tablet(result[1], result[2], Double.parseDouble(result[3]), Double.parseDouble(result[4]), result[5], result[6], result[7],
                        Integer.parseInt(result[8]), Integer.parseInt(result[9]), Integer.parseInt(result[10]), Double.parseDouble(result[11]), result[12], result[13], result[14], Integer.parseInt(result[17]));

                products[currentProductNum] = tl;
                currentProductNum++;
            }
            if (Integer.parseInt(result[17]) == 4) {
                //1/2/3/4/5/6/7/8/9/10/
                Desktop dt = new Desktop(result[1], result[2], Double.parseDouble(result[3]), Double.parseDouble(result[4]), result[5], result[6], result[7],
                        Integer.parseInt(result[8]), Integer.parseInt(result[9]), Integer.parseInt(result[10]), Integer.parseInt(result[17]));

                products[currentProductNum] = dt;
                currentProductNum++;
            }
            if (Integer.parseInt(result[17]) == 5) {
                //1/2/3/4/5/7/8/9/10/11/12/13/15/16
                Laptop lp = new Laptop(result[1], result[2], Double.parseDouble(result[3]), Double.parseDouble(result[4]), result[5], result[7],
                        Integer.parseInt(result[8]), Integer.parseInt(result[9]), Integer.parseInt(result[10]), Double.parseDouble(result[11]), result[12], result[13],
                        Boolean.parseBoolean(result[15]), Boolean.parseBoolean(result[16]), Integer.parseInt(result[17]));

                products[currentProductNum] = lp;
                currentProductNum++;
            }
        }
        return products;

    }

}
