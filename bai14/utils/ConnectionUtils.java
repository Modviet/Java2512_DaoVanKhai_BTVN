package com.example.bai14.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtils {

    private static String url ="jdbc:mysql://localhost:3306/quanlysinhvien";
    private static String username="root";
    private static String password="Mod123456@";

    public static Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            return connection;
        } catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection){
        if(connection != null){
            try{
                connection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
