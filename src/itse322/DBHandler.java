/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itse322;

// Imports
import java.sql.*;

/**
 *
 * @author Zenjar
 */
public class DBHandler {
    // Drivers and URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/gym";
    
    // Credentials
    static final String USER = "root";
    static final String PASS = "Aq12wsaq1--11";
    
    // Database Objects
    public static Connection conn = null;
    static Statement statement = null;
    static PreparedStatement preStatement = null;
    static ResultSet rs = null;
    
    public static void init() {
        // Logging
        LogHandler.info("Connecting to the Database");
        
        // Init JDBC Driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException exp) {
            // Logging
            LogHandler.fatal("JDBC Driver not Found");
        }
        
        // Connect to Database
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException exp) {
            // Logging
            LogHandler.fatal("Failed to Connect to the Database");
        }
        
        // Setup Statement Object
        try {
            statement = conn.createStatement();
        } catch (SQLException exp) {
            // Logging
            LogHandler.fatal("Failed to Create Database Statement Object");
        }
    }
    
    public static boolean isConnected() {
        // Get if the Database is Connected or not
        return conn != null;
    }
    
    public static int update(String sql) {
        // Use JDBC Statement to Execute Update SQL and Return Rows Affected
        int updateCount = 0;
        try {
            updateCount = statement.executeUpdate(sql);
        } catch (SQLException exp) {
            // Logging
            LogHandler.error("Failed to Execute Database Query");
        }
        return updateCount;
    }
    
    public static int update(String sql, Object ... inputs) {
        // Use JDBC Prepared Statement to Execute Update SQL and Return Rows Affected
        int updateCount = 0;
        int inputsSize = inputs.length;
        try {
            // Loop Through the inputs and Add them to the Statment
            // Then Execute Statement
            preStatement = conn.prepareStatement(sql);
            for (int i = 0; i < inputsSize; i++) {
                preStatement.setObject(i+1, inputs[i]);
            }
            updateCount = preStatement.executeUpdate();
        } catch (SQLException exp) {
            // Logging
            LogHandler.error("Failed to Execute Database Query");
        }
        return updateCount;
    }
    
    public static ResultSet query(String sql) {
        // Use JDBC Statement to Execute Update SQL and Return Resulting Rows
        try {
            rs = statement.executeQuery(sql);
        } catch (SQLException exp) {
            // Logging
            LogHandler.error("Failed to Execute Database Query");
        }
        
        return rs;
    }
    
    public static ResultSet query(String sql, Object ... inputs) {
        // Use JDBC Prepared Statement to Execute Update SQL and Return Resulting Rows
        int inputsSize = inputs.length;
        try {
            // Loop Through the inputs and Add them to the Statment
            // Then Execute Statement
            preStatement = conn.prepareStatement(sql);
            for (int i = 0; i < inputsSize; i++) {
                preStatement.setObject(i+1, inputs[i]);
            }
            rs = preStatement.executeQuery();
        } catch (SQLException exp) {
            // Logging
            LogHandler.error("Failed to Execute Database Query");
        }
        
        return rs;
    }
    
    public static void close() {
        // Close Connection to the Database
        try {
            // Logging
            LogHandler.info("Closing Connection to the Database");
            
            conn.close();
        } catch (SQLException exp) {
            // Logging
            LogHandler.info("Failed to Close Connection to the Database");
        }
        
        // Logging
        LogHandler.info("Closed Connection to the Database Successfully");
    }
}
