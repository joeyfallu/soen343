package com.example.myapp.database;

import com.example.myapp.productCatalog.*;
import com.example.myapp.purchases.Purchase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PurchaseTDG {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public void dbConnect() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager
                .getConnection("jdbc:mysql://soen343-mysql.cutkgxnrwyh2.us-east-1.rds.amazonaws.com:3306/electronics","soen343team","spiderman");
        statement = connect.createStatement();
    }

    public void dbInsert(Purchase purchase) throws Exception {

        Product pro = purchase.getProduct();
        int userId = purchase.getUserId();
        String timeStamp = purchase.getTimeStamp();

        dbConnect();
        String sql = "";
        if(pro instanceof Monitor){
            Monitor mn = (Monitor)pro;
            sql="INSERT INTO Purchases (serialNumber, model, weight, price, brand, size,discriminator, timeStamp, userId) VALUES ('"+ mn.getSerialNumber() +"','"+mn.getModel()+"','"+mn.getWeight()
                    +"','"+mn.getPrice()+"','"+mn.getBrand()+"','"+mn.getSize()+"','2','"+timeStamp+"','"+userId+"')";
        }
        else if(pro instanceof Tablet){
            Tablet tb = (Tablet)pro;
            sql="INSERT INTO Purchases (serialNumber, model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize,dimensions, batteryInfo,operatingSystem,cameraInfo,size,discriminator, timeStamp, userId) VALUES ('"+ tb.getSerialNumber() +"','"+tb.getModel()+
                    "','"+tb.getWeight()+"','"+tb.getPrice()+"','"+tb.getBrand()+"','"+tb.getProcessorType()+"','"+tb.getCpuCores()+"','"+tb.getRam()+"','"+tb.getHardDriveSize()+"','"+tb.getDimensions()+"','"+
                    tb.getBatteryInfo()+"','"+tb.getOperatingSystem()+"','"+tb.getCameraInfo()+"','"+tb.getSize()+"','3','"+timeStamp+"','"+userId+"')";
        }
        else if(pro instanceof Desktop){
            Desktop dt = (Desktop)pro;
            sql="INSERT INTO Purchases (serialNumber, model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize,dimensions,discriminator, timeStamp, userId) VALUES ('"+ dt.getSerialNumber() +"','"+dt.getModel()+
                    "','"+dt.getWeight()+"','"+dt.getPrice()+"','"+dt.getBrand()+"','"+dt.getProcessorType()+"','"+dt.getCpuCores()+"','"+dt.getRam()+"','"+dt.getHardDriveSize()+"','"+dt.getDimensions()+"','4','"+timeStamp+"','"+userId+"')";
        }
        else if(pro instanceof Laptop){
            Laptop lp = (Laptop)pro;
            sql="INSERT INTO Purchases (serialNumber, model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize,size, batteryInfo,operatingSystem,camera,touchScreen,discriminator, timeStamp, userId) VALUES ('"+lp.getSerialNumber() +"','"+lp.getModel()+
                    "','"+lp.getWeight()+"','"+lp.getPrice()+"','"+lp.getBrand()+"','"+lp.getProcessorType()+"','"+lp.getCpuCores()+"','"+lp.getRam()+"','"+lp.getHardDriveSize()+"','"+lp.getSize()+"','"+
                    lp.getBatteryInfo()+"','"+lp.getOperatingSystem()+"','"+lp.getCamera()+"','"+lp.getTouchScreen()+"','5','"+timeStamp+"','"+userId+"')";
        }
        statement.executeUpdate(sql);
    }

    public Purchase dbGet(String serialNumber) throws Exception {
        dbConnect();
        String sql = "SELECT * FROM Purchases WHERE serialNumber='" + serialNumber + "'";
        resultSet = statement.executeQuery(sql);
        String result[] = new String[20];

        while (resultSet.next())
        {
            for (int i = 1; i < 20; i++) {
                result[i] = resultSet.getString(i);
            }
        }
        if (Integer.parseInt(result[17]) == 2)
        {
            //1/2/3/4/5/11
            Monitor monitor = new Monitor(result[1], result[2], Double.parseDouble(result[3]), Double.parseDouble(result[4]), result[5], Integer.parseInt(result[11]), Integer.parseInt(result[17]));
            return new Purchase(Integer.parseInt(result[19]),result[18],monitor);
        }
        else if (Integer.parseInt(result[17]) == 3)
        {
            //1/2/3/4/5/6/7/8/9/10/11/12/13/14/15/
            Tablet tablet = new Tablet(result[1], result[2], Double.parseDouble(result[3]), Double.parseDouble(result[4]), result[5], result[6], result[7],
                    Integer.parseInt(result[8]), Integer.parseInt(result[9]), Integer.parseInt(result[10]), Double.parseDouble(result[11]), result[12], result[13], result[14], Integer.parseInt(result[17]));
            return new Purchase(Integer.parseInt(result[19]),result[18],tablet);
        }
        else if (Integer.parseInt(result[17]) == 4)
        {
            //1/2/3/4/5/6/7/8/9/10/
            Desktop desktop = new Desktop(result[1], result[2], Double.parseDouble(result[3]), Double.parseDouble(result[4]), result[5], result[6], result[7],
                    Integer.parseInt(result[8]), Integer.parseInt(result[9]), Integer.parseInt(result[10]), Integer.parseInt(result[17]));
            return new Purchase(Integer.parseInt(result[19]),result[18],desktop);
        }
        else if (Integer.parseInt(result[17]) == 5)
        {
            //1/2/3/4/5/7/8/9/10/11/12/13/15/16
            Laptop laptop = new Laptop(result[1], result[2], Double.parseDouble(result[3]), Double.parseDouble(result[4]), result[5], result[7],
                    Integer.parseInt(result[8]), Integer.parseInt(result[9]), Integer.parseInt(result[10]), Double.parseDouble(result[11]), result[12], result[13],
                    Boolean.parseBoolean(result[15]), Boolean.parseBoolean(result[16]), Integer.parseInt(result[17]));
            return new Purchase(Integer.parseInt(result[19]),result[18],laptop);
        }
        return null;
    }

    public void dbDelete(String serialNumber) throws Exception {
        dbConnect();
        String sql = "DELETE FROM Purchases WHERE serialNumber = '" + serialNumber + "'";
        statement.executeUpdate(sql);
    }

    public Purchase[] dbGetAll() throws Exception{
        dbConnect();
        String sql = "SELECT * FROM Purchases";
        int numberOfPurchase = 0;
        int currentPurchaseNum = 0;

        resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            numberOfPurchase++;
        }

        Purchase[] purchaseList = new Purchase[numberOfPurchase];
        String[] result = new String[20];

        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            for (int i = 1; i < 20; i++) {
                result[i] = resultSet.getString(i);
            }

            if (Integer.parseInt(result[17]) == 2) {
                //1/2/3/4/5/11
                Monitor monitor = new Monitor(result[1], result[2], Double.parseDouble(result[3]), Double.parseDouble(result[4]), result[5], Integer.parseInt(result[11]), Integer.parseInt(result[17]));
                purchaseList[currentPurchaseNum] = new Purchase(Integer.parseInt(result[19]), result[18], monitor);
                currentPurchaseNum++;
            }
            else if (Integer.parseInt(result[17]) == 3) {
                //1/2/3/4/5/6/7/8/9/10/11/12/13/14/15/
                Tablet tablet = new Tablet(result[1], result[2], Double.parseDouble(result[3]), Double.parseDouble(result[4]), result[5], result[6], result[7],
                        Integer.parseInt(result[8]), Integer.parseInt(result[9]), Integer.parseInt(result[10]), Double.parseDouble(result[11]), result[12], result[13], result[14], Integer.parseInt(result[17]));
                purchaseList[currentPurchaseNum] = new Purchase(Integer.parseInt(result[19]), result[18], tablet);
                currentPurchaseNum++;
            }
            else if (Integer.parseInt(result[17]) == 4) {
                //1/2/3/4/5/6/7/8/9/10/
                Desktop desktop = new Desktop(result[1], result[2], Double.parseDouble(result[3]), Double.parseDouble(result[4]), result[5], result[6], result[7],
                        Integer.parseInt(result[8]), Integer.parseInt(result[9]), Integer.parseInt(result[10]), Integer.parseInt(result[17]));
                purchaseList[currentPurchaseNum] = new Purchase(Integer.parseInt(result[19]), result[18], desktop);
                currentPurchaseNum++;
            }
            else if (Integer.parseInt(result[17]) == 5) {
                //1/2/3/4/5/7/8/9/10/11/12/13/15/16
                Laptop laptop = new Laptop(result[1], result[2], Double.parseDouble(result[3]), Double.parseDouble(result[4]), result[5], result[7],
                        Integer.parseInt(result[8]), Integer.parseInt(result[9]), Integer.parseInt(result[10]), Double.parseDouble(result[11]), result[12], result[13],
                        Boolean.parseBoolean(result[15]), Boolean.parseBoolean(result[16]), Integer.parseInt(result[17]));
                purchaseList[currentPurchaseNum] = new Purchase(Integer.parseInt(result[19]), result[18], laptop);
                currentPurchaseNum++;
            }
        }
        return purchaseList;
    }


}

