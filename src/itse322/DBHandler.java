/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itse322;

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
            System.out.println(exp);
        }
        
        // Setup Statement Object
        try {
            statement = conn.createStatement();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
    }
    
    public static int update(String sql) {
        int updateCount = 0;
        try {
            updateCount = statement.executeUpdate(sql);
        } catch (SQLException exp) {
            System.out.println(exp);
        }
        return updateCount;
    }
    
    public static int update(String sql, Object ... inputs) {
        int updateCount = 0;
        int inputsSize = inputs.length;
        try {
            preStatement = conn.prepareStatement(sql);
            for (int i = 0; i < inputsSize; i++) {
                preStatement.setObject(i+1, inputs[i]);
            }
            updateCount = preStatement.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
        return updateCount;
    }
    
    public static ResultSet query(String sql) {
        try {
            rs = statement.executeQuery(sql);
        } catch (SQLException exp) {
            System.out.println(exp);
        }
        
        return rs;
    }
    
    public static ResultSet query(String sql, Object ... inputs) {
        int inputsSize = inputs.length;
        try {
            preStatement = conn.prepareStatement(sql);
            for (int i = 0; i < inputsSize; i++) {
                preStatement.setObject(i+1, inputs[i]);
            }
            rs = preStatement.executeQuery();
        } catch (SQLException exp) {
            System.out.println(exp);
        }
        
        return rs;
    }
    
    public static void close() {
        try {
            System.out.println("Closing Connection to Database...");
            rs.close();
            statement.close();
            preStatement.close();
            conn.close();
        } catch (SQLException exp) {
            System.out.println("Couldn't Close Database");
        }
    }
}
