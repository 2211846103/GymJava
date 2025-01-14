/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itse322;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 
 */
public class ScheduleDA {
    public static void createSchedule(Schedule s) {
        // Logging
        LogController.info("Adding a Schedule to the Database");
        
        // Add the Schedule's Info to the Database
        DBHandler.update("INSERT INTO schedules (weekday, client_id) VALUES(?, ?)",
                s.getWeekDay(),
                s.getClientId()
        );
        
        // Get Generated Id for the Schedule and Add it to the Object's data
        ResultSet idResults = DBHandler.query("SELECT LAST_INSERT_ID() FROM schedules");
        try {
            if(!idResults.next()) throw new SQLException("Schedule Creation Failed");
             s.setId(idResults.getInt("LAST_INSERT_ID()"));
        } catch (SQLException exp) {
            // Logging
            LogController.error("Failed to Create a New Schedule");
        }
        
        // Logging
        LogController.info("Schedule Created Successfully");
    }
    public static ArrayList<Schedule> getSchedulesByClient(Client client) {
        // Logging
        LogController.info("Gathering Specified Schedule's Info");
        
        // Get Schedules Based on a Client's ID
        ResultSet rs = DBHandler.query("SELECT * FROM schedules WHERE client_id=?", client.getId());
        ArrayList<Schedule> schedules = new ArrayList<>();
        
        // Add the Schedules to a List
        try {
            while (rs.next()) {
                Schedule s = new Schedule();
                
                s.setId(rs.getInt("id"));
                s.setWeekDay(rs.getString("weekday"));
                s.setClientId(rs.getInt("client_id"));
                
                schedules.add(s);
            }
        } catch (SQLException exp) {
            // Logging
            LogController.error("Failed to Find Specified Schedule");
        }
        
        // Logging
        LogController.info("Found Specified Schedule");
        
        // Return Schedules List
        return schedules;
    }
    public static void deleteSchedule(int id) {
        // Logging
        LogController.warn("Removing a Schedule from the Database");
        
        // Remove Schedule From Database Based on Schedule's ID
        DBHandler.update("DELETE FROM schedules WHERE id=?", id);
        
        // Logging
        LogController.warn("Removed Schedule from the Datbase Successfully");
    }
}
