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
    public static String checkUser(int userID, String userPass) {
        ResultSet users = DBHandler.query("SELECT * FROM users_creds WHERE user_id=? AND user_pass=?", userID, userPass); 
        String type = "";
        
        try {
            if (users.next()) type = users.getString("user_type");
        } catch (SQLException exp) {
            System.out.println(exp);
        }
        
        return type;
    }
}
