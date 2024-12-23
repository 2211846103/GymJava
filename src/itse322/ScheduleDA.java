/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itse322;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Zenjar
 */
public class ScheduleDA {
    public static void createSchedule(Schedule s) {
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
            exp.printStackTrace();
        }
    }
    public static ArrayList<Schedule> getSchedulesByClient(Client client) {
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
            exp.printStackTrace();
        }
        
        // Return Schedules List
        return schedules;
    }
    public static void deleteSchedule(int id) {
        // Remove Schedule From Database Based on Schedule's ID
        DBHandler.update("DELETE FROM schedules WHERE id=?", id);
    }
}
