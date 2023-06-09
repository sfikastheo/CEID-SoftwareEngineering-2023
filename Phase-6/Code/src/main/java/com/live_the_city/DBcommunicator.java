package com.live_the_city;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBcommunicator {
    
    private static Connection databaseLink;
    
    public DBcommunicator(){
        String databaseName = "LIVE_THE_CITY_SEPR2023";
        String databaseUser = "root";
        String databasePassword = "maria";
        
        try {
            databaseLink = DriverManager.getConnection("jdbc:mariadb://localhost:3306/" + databaseName + "?user=" + databaseUser + "&password=" + databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
       return databaseLink;
    }
}
