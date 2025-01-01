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
public class LoginHandler {
    public static boolean login(int userID, String userPass) {
        // Logging
        LogHandler.info("Looking for User in the Database");
        
        // Get Specified User
        ResultSet rs = DBHandler.query("SELECT * FROM users_creds WHERE user_id=? AND user_pass=?", userID, userPass); 
        String type = "";
        
        // If the User Doesn't Exist Return False
        try {
            if (!rs.next()) return false;
            type = rs.getString("user_type");
        } catch (SQLException exp) {
            // Logging
            LogHandler.error("Failed to Find Specified Credentials");
        }
        
        // Logging
        LogHandler.info("User Found Successfully");
        LogHandler.info("Opening Appropriate Interface");
        
        // If the User Exists Open Appropriate Window
        switch (type) {
            case "admin":
                new AdminUI().setVisible(true);
                break;
            case "client":
                Client client = ClientDA.getClientById(userID);
                new ClientUI(client).setVisible(true);
                break;
            case "coach":
                Coach coach = CoachDA.getCoachById(userID);
                new CoachUI(coach).setVisible(true);
        }
        
        // Logging
        LogHandler.info("Interface Opened Successfully");
        
        // Return True For Success
        return true;
    }
}
