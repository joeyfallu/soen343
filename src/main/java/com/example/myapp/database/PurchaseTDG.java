package com.example.myapp.database;

import com.example.myapp.productCatalog.*;

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

    public int dbInsert(Product pro, int userId, String timeStamp) throws Exception {

        dbConnect();
        String sql = "";
        if(pro instanceof Monitor){
            Monitor mn = (Monitor)pro;
            sql="INSERT INTO Purchases (model, weight, price, brand, size,discriminator, timeStamp, userId) VALUES ('"+mn.getModel()+"','"+mn.getWeight()
                    +"','"+mn.getPrice()+"','"+mn.getBrand()+"','"+mn.getSize()+"','2','"+timeStamp+"','"+userId+"')";
        }
        else if(pro instanceof Tablet){
            Tablet tb = (Tablet)pro;
            sql="INSERT INTO Purchases (model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize,dimensions, batteryInfo,operatingSystem,cameraInfo,size,discriminator, timeStamp, userId) VALUES ('"+tb.getModel()+
                    "','"+tb.getWeight()+"','"+tb.getPrice()+"','"+tb.getBrand()+"','"+tb.getProcessorType()+"','"+tb.getCpuCores()+"','"+tb.getRam()+"','"+tb.getHardDriveSize()+"','"+tb.getDimensions()+"','"+
                    tb.getBatteryInfo()+"','"+tb.getOperatingSystem()+"','"+tb.getCameraInfo()+"','"+tb.getSize()+"','3','"+timeStamp+"','"+userId+"')";
        }
        else if(pro instanceof Desktop){
            Desktop dt = (Desktop)pro;
            sql="INSERT INTO Purchases (model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize,dimensions,discriminator, timeStamp, userId) VALUES ('"+dt.getModel()+
                    "','"+dt.getWeight()+"','"+dt.getPrice()+"','"+dt.getBrand()+"','"+dt.getProcessorType()+"','"+dt.getCpuCores()+"','"+dt.getRam()+"','"+dt.getHardDriveSize()+"','"+dt.getDimensions()+"','4','"+timeStamp+"','"+userId+"')";
        }
        else if(pro instanceof Laptop){
            Laptop lp = (Laptop)pro;
            sql="INSERT INTO Purchases (model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize,size, batteryInfo,operatingSystem,camera,touchScreen,discriminator, timeStamp, userId) VALUES ('"+lp.getModel()+
                    "','"+lp.getWeight()+"','"+lp.getPrice()+"','"+lp.getBrand()+"','"+lp.getProcessorType()+"','"+lp.getCpuCores()+"','"+lp.getRam()+"','"+lp.getHardDriveSize()+"','"+lp.getSize()+"','"+
                    lp.getBatteryInfo()+"','"+lp.getOperatingSystem()+"','"+lp.getCamera()+"','"+lp.getTouchScreen()+"','5','"+timeStamp+"','"+userId+"')";
        }
        statement.executeUpdate(sql);
        resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() FROM Purchases");
        int id=-1;
        while(resultSet.next()){
            id = resultSet.getInt(1);
        }

        return id;

    }

    public Product dbGet(int id) throws Exception{
        dbConnect();
        String sql = "SELECT * FROM Purchases WHERE id='" + id + "'";
        resultSet = statement.executeQuery(sql);
        String result[]= new String[20];


        while(resultSet.next())
        {
            for(int i=1; i<20; i++) {
                result[i] = resultSet.getString(i);
            }

        }
        if(Integer.parseInt(result[17])==2)
        {
            //1/2/3/4/5/11
            Monitor mn = new Monitor(Integer.parseInt(result[1]),result[2],Double.parseDouble(result[3]),Double.parseDouble(result[4]),result[5],Integer.parseInt(result[11]), Integer.parseInt(result[17]));
            return mn;
        }
        if(Integer.parseInt(result[17])==3)
        {
            //1/2/3/4/5/6/7/8/9/10/11/12/13/14/15/
            Tablet tl= new Tablet(Integer.parseInt(result[1]),result[2],Double.parseDouble(result[3]),Double.parseDouble(result[4]),result[5],result[6],result[7],
                    Integer.parseInt(result[8]),Integer.parseInt(result[9]),Integer.parseInt(result[10]),Double.parseDouble(result[11]),result[12],result[13],result[14], Integer.parseInt(result[17]));
            return tl;
        }
        if(Integer.parseInt(result[17])==4)
        {
            //1/2/3/4/5/6/7/8/9/10/
            Desktop dt = new Desktop(Integer.parseInt(result[1]),result[2],Double.parseDouble(result[3]),Double.parseDouble(result[4]),result[5],result[6],result[7],
                    Integer.parseInt(result[8]),Integer.parseInt(result[9]),Integer.parseInt(result[10]), Integer.parseInt(result[17]));
            return dt;
        }
        if(Integer.parseInt(result[17])==5)
        {
            //1/2/3/4/5/7/8/9/10/11/12/13/15/16
            Laptop lp = new Laptop(Integer.parseInt(result[1]),result[2],Double.parseDouble(result[3]),Double.parseDouble(result[4]),result[5],result[7],
                    Integer.parseInt(result[8]),Integer.parseInt(result[9]),Integer.parseInt(result[10]),Double.parseDouble(result[11]),result[12],result[13],
                    Boolean.parseBoolean(result[15]),Boolean.parseBoolean(result[16]), Integer.parseInt(result[17]));
            return lp;
        }
        return null;
    }
    public void dbDelete(int id) throws Exception {
        dbConnect();
        String sql = "DELETE FROM Purchases WHERE id = '" + id + "'";
        statement.executeUpdate(sql);
    }


    public Product[] dbGetAll() throws Exception{
        dbConnect();
        String sql = "SELECT * FROM Purchases";
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
                Monitor mn = new Monitor(Integer.parseInt(result[1]), result[2], Double.parseDouble(result[3]), Double.parseDouble(result[4]), result[5], Integer.parseInt(result[11]), Integer.parseInt(result[17]));
                products[currentProductNum] = mn;
                currentProductNum++;
            }
            if (Integer.parseInt(result[17]) == 3) {
                //1/2/3/4/5/6/7/8/9/10/11/12/13/14/15/
                Tablet tl = new Tablet(Integer.parseInt(result[1]), result[2], Double.parseDouble(result[3]), Double.parseDouble(result[4]), result[5], result[6], result[7],
                        Integer.parseInt(result[8]), Integer.parseInt(result[9]), Integer.parseInt(result[10]), Double.parseDouble(result[11]), result[12], result[13], result[14], Integer.parseInt(result[17]));

                products[currentProductNum] = tl;
                currentProductNum++;
            }
            if (Integer.parseInt(result[17]) == 4) {
                //1/2/3/4/5/6/7/8/9/10/
                Desktop dt = new Desktop(Integer.parseInt(result[1]), result[2], Double.parseDouble(result[3]), Double.parseDouble(result[4]), result[5], result[6], result[7],
                        Integer.parseInt(result[8]), Integer.parseInt(result[9]), Integer.parseInt(result[10]), Integer.parseInt(result[17]));

                products[currentProductNum] = dt;
                currentProductNum++;
            }
            if (Integer.parseInt(result[17]) == 5) {
                //1/2/3/4/5/7/8/9/10/11/12/13/15/16
                Laptop lp = new Laptop(Integer.parseInt(result[1]), result[2], Double.parseDouble(result[3]), Double.parseDouble(result[4]), result[5], result[7],
                        Integer.parseInt(result[8]), Integer.parseInt(result[9]), Integer.parseInt(result[10]), Double.parseDouble(result[11]), result[12], result[13],
                        Boolean.parseBoolean(result[15]), Boolean.parseBoolean(result[16]), Integer.parseInt(result[17]));

                products[currentProductNum] = lp;
                currentProductNum++;
            }
        }
        return products;

    }
}
