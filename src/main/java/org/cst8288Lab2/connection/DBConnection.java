/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cst8288Lab2.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This Singleton class controls the database connections for the application. The
 * class ensure that only one instance of itself and its connection object are
 * created. The objects were separated this way to allow for user input of 
 * database credentials.
 * @author Dan Blais
 * @version Java 17
 */
public class DBConnection {
	
    private static DBConnection dbConnection = null;
    private static Connection connection = null;
    
    private static String serverUrl;
    private static String userString;
    private static String passwordString;
    private static String driverString = "com.mysql.cj.jdbc.Driver";

    /**
     * Three parameter constructor which accepts the user's database credentials.
     * @param serverUrl The server URL
     * @param userString The username
     * @param passwordString The password
     */
    private DBConnection(Properties properties) {
        this.serverUrl = "jdbc:" + properties.getProperty("db") + "://" + properties.getProperty("host") + ":" + properties.getProperty("port") + "/" + properties.getProperty("name");
        this.userString = properties.getProperty("user");
        this.passwordString = properties.getProperty("pass");
    }
	
    /**
     * This method ensures that only a single instance of this class is created.
     * It accepts the user's credentials so that when the class is instantiated with
     * the first time the class variables are populated with the passed credentials.
     * @param server The server URL
     * @param userName The username
     * @param password The 
     * @return A DBConnection object
     */
    public static DBConnection getInstance(Properties properties) {
        if(dbConnection == null) {
            dbConnection = new DBConnection(properties);
        }
        return dbConnection;
    }
    
    /**
     * This method ensures that only a single instance of a Connection object is
     * created. 
     * @return A Connection object
     */
    public static Connection connect() {
        if(connection == null) {
            try {
                Class.forName(driverString);
                connection = DriverManager.getConnection(serverUrl, userString, passwordString);
                System.out.println("Connected");
            } catch(Exception e) {
                Logger.getAnonymousLogger().log(Level.SEVERE, e.toString());
            }
        }
        return connection;
    }
}

