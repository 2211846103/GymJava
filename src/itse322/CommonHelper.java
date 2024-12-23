/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itse322;

// Imports
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Zenjar
 */
public class CommonHelper {
    //Methods
    public static boolean sendError(JLabel errorLabel, String message) {
        // Setup the Message and Make the Error Visible
        errorLabel.setText(message);
        errorLabel.setVisible(true);
        return false;
    }
    
    public static int calculateAge(java.util.Date birthDate) {
        // Calculate Age Given a Birth Date
        long milliseconds = System.currentTimeMillis() - birthDate.getTime();
        int years = (int) (milliseconds / (1000L * 60 * 60 * 24 * 365));
        return years;
    }
    
    public static DefaultTableModel getUserIdTable(ArrayList<? extends User> users) {
        // Initiate table model
        DefaultTableModel table = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        table.addColumn("ID");
        table.addColumn("Full Name");
        
        // Add Values to table
        Object[] data = new Object[2];
        for (User c : users) {
            data[0] = c.getId();
            data[1] = c.toString();

            table.addRow(data);
        }
        
        // Add option to "Add new user"
        data[0] = "Add";
        data[1] = "";
        table.addRow(data);
        
        return table;
    }
    
    public static DefaultComboBoxModel createComboBox(ArrayList<?> items) {
        // Setup ComboBox and Add items
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("--Select");
        for (Object c : items) {
            model.addElement(c);
        }
        
        // Return ComboBox
        return model;
    }
    
    public static java.sql.Date getSQLDate(JSpinner spinner) {
        // Convert The Value of a JSpinner to SQL Date Object
        return new java.sql.Date(
                ((java.util.Date) spinner.getValue()).getTime()
        );
    }
    
    public static String getPasswordString(JPasswordField field) {
        // Convert The Value of a JPasswordField to String
        return new String(field.getPassword());
    }
    
    public static int addCredential(String type, String password) {
        // Add the User to the Database
        DBHandler.update("INSERT INTO users_creds (user_type, user_pass) VALUES(?, ?)", type, password);
            
        // Get the Id of the New User
        int id = -1;
        ResultSet idResults = DBHandler.query("SELECT LAST_INSERT_ID() FROM users_creds");
        try {
            if(!idResults.next()) throw new SQLException("User Creation Failed");
            id = idResults.getInt("LAST_INSERT_ID()");
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
        
        // Return Retrieved ID
        return id;
    }
    
    public static int parseGender(String gender) {
        // Convert A Gender to 0 for Males and 1 for Females
        return (gender.equals("male")) ? 0 : 1;
    }
    
    public static int parseTier(String tier) {
        // Convert a Tier to 0 for Basic Tier and 1 for Premium Tier
        return (tier.equals("basic")) ? 0 : 1;
    }
    
    public static java.sql.Date getDateAfterNDays(int n) {
        // Get the current date using LocalDate
        LocalDate today = LocalDate.now();

        // Add n days to the current date
        LocalDate futureDate = today.plusDays(n);

        // Convert LocalDate to java.sql.Date
        return java.sql.Date.valueOf(futureDate);
      }
}
