package com.example.myapp.database;

import com.example.myapp.userCatalog.*;

import java.sql.*;


public class UserTDG {

    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public void dbConnect() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager
                .getConnection("jdbc:mysql://soen343-mysql.cutkgxnrwyh2.us-east-1.rds.amazonaws.com:3306/electronics","soen343team","spiderman");
        statement = connect.createStatement();
    }

    public int dbInsert(User user) throws Exception
    {
        dbConnect();
        String sql="INSERT INTO User (firstName, lastName, address, phoneNumber, email, password, isAdmin) VALUES ('"+user.getFirstName()+"','"+user.getLastName()+"','"
                +user.getAddress()+"','"+user.getPhoneNumber()+"','"+user.getEmail()+"','"+user.getPassword()+"','"+user.getIsAdmin()+"')";
        statement.executeUpdate(sql);
        resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() FROM User");
        int id=-1;
        while(resultSet.next()){
            id = resultSet.getInt(1);
        }
        return id;
    }

    public User dbGet(int id) throws Exception
    {
        dbConnect();
        User user = new User();
        String sql = "SELECT * FROM User WHERE id='" + id + "'";
        resultSet = statement.executeQuery(sql);
        String result[]= new String[9];
        while(resultSet.next())
        {
            for(int i=1; i<9; i++) {
                result[i] = resultSet.getString(i);
            }

        }
        user = new User(Integer.parseInt(result[1]),result[2],result[3],result[4],result[5],result[6],result[7],Integer.parseInt(result[8]));

        return user;

    }




}