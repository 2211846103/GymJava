/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itse322;

// Imports
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Zenjar
 */
public class CoachDA {
    // Methods
    public static void createCoach(Coach coach, String password) {
        // Logging
        LogHandler.info("Adding User to the Database");
        
        // Add Coach's Password to the Database
        DBHandler.update("INSERT INTO users_creds (user_type, user_pass) VALUES('coach', ?)", password);
        
        // Get Generated ID of the Coach
        ResultSet idResults = DBHandler.query("SELECT LAST_INSERT_ID() FROM users_creds");
        try {
            if(!idResults.next()) throw new SQLException("User Creation Failed");
             coach.setId(idResults.getInt("LAST_INSERT_ID()"));
        } catch (SQLException exp) {
            // Logging
            LogHandler.error("Failed to Create a New User");
        }
        
        // Add Coach's Data to the Database
        DBHandler.update("INSERT INTO coaches VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                coach.getId(),
                coach.getFirstName(),
                coach.getLastName(),
                new java.sql.Date(coach.getBirthDate().getTime()),
                coach.getPhoneNumber(),
                coach.getEmail(),
                coach.getFirstYear(),
                coach.getSalary()
        );
        
        // Logging
        LogHandler.info("Added User to the Database Successfully");
    }
    public static Coach getCoachById(int id) {
        // Logging
        LogHandler.info("Gathering Info about Specified User");
        
        // Get Results from the Database and Initiate Coach Object
        ResultSet rs = DBHandler.query("SELECT * FROM coaches WHERE id=?", id);
        Coach user = new Coach();
        user.setId(id);
        
        // Assign Values from the Database to the Coach Object
        try {
            while (rs.next()) {
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setBirthDate(rs.getDate("birth_date"));
                user.setPhoneNumber(rs.getString("tel"));
                user.setEmail(rs.getString("email"));
                user.setFirstYear(rs.getDate("first_year").toLocalDate().getYear());
                user.setSalary(rs.getFloat("salary"));
            }
        } catch (SQLException exp) {
            // Logging
            LogHandler.error("Failed to Find Specified User");
        }
        
        // Logging
        LogHandler.info("Found Specified User's Info");
        
        // Return Retrieved User
        return user;
    }
    public static ArrayList<Coach> getAllCoachs() {
        // Logging
        LogHandler.info("Assembling a List of all Coaches in the Database");
        
        // Get Results from the Database and Initiate ArrayList
        ResultSet rs = DBHandler.query("SELECT * FROM coaches");
        ArrayList<Coach> users = new ArrayList<>();
        
        // Assign Values from the Database to the Coach Object
        try {
            while (rs.next()) {
                Coach user = new Coach();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setBirthDate(rs.getDate("birth_date"));
                user.setPhoneNumber(rs.getString("tel"));
                user.setEmail(rs.getString("email"));
                user.setFirstYear(rs.getDate("first_year").toLocalDate().getYear());
                user.setSalary(rs.getFloat("salary"));
                
                users.add(user);
            }
        } catch (SQLException exp) {
            // Logging
            LogHandler.error("Failed to Collect Coaches in the System");
        }
        
        // Logging
        LogHandler.info("Gathered Coaches in the System Successfully");
        
        // Return Retrieved Users
        return users;
    }
    public static void updateCoach(Coach coach) {
        // Logging
        LogHandler.info("Updating User's Info in the Database");
        
        // Update the Database based on the Id of the Coach
        DBHandler.update("UPDATE coaches SET first_name=?, last_name=?, birth_date=?, tel=?, email=?, first_year=?, salary=? WHERE id=?",
                coach.getFirstName(),
                coach.getLastName(),
                new java.sql.Date(coach.getBirthDate().getTime() + (1000 * 60 * 60 * 24)),
                coach.getPhoneNumber(),
                coach.getEmail(),
                coach.getFirstYear(),
                coach.getSalary(),
                coach.getId()
        );
        
        // Logging
        LogHandler.info("Updated User's Info in the Database Successfully");
    }
    public static void deleteCoach(int id) {
        // Logging
        LogHandler.warn("Removing a User from the Databse");
        
        // Delete Coach from the Database
        DBHandler.update("DELETE FROM coaches WHERE id=?", id);
        
        // Logging
        LogHandler.warn("Removed a User from the Databse Successfully");
    }
}
