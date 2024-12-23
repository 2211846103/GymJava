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
        // Init JDBC Driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException exp) {
            System.out.println("JDBC Driver not Found");
        }
        
        // Connect to Database
        System.out.println("Connecting to Database...");
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
        
        // Setup Statement Object
        try {
            statement = conn.createStatement();
        } catch (SQLException exp) {
            exp.printStackTrace();
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
            exp.printStackTrace();
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
            exp.printStackTrace();
        }
        return updateCount;
    }
    
    public static ResultSet query(String sql) {
        // Use JDBC Statement to Execute Update SQL and Return Resulting Rows
        try {
            rs = statement.executeQuery(sql);
        } catch (SQLException exp) {
            exp.printStackTrace();
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
            exp.printStackTrace();
        }
        
        return rs;
    }
    
    public static void close() {
        // Close Connection to the Database
        try {
            System.out.println("Closing Connection to Database...");
            rs.close();
            statement.close();
            preStatement.close();
            conn.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }
}
