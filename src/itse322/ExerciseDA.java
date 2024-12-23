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
public class ExerciseDA {
    public static void createExercise(Exercise e) {
        // Add the Exercise to the Database
        DBHandler.update("INSERT INTO exercises (name, sets, reps, e_order, schedule_id) VALUES(?, ?, ?, ?, ?)",
                e.getName(),
                e.getSets(),
                e.getReps(),
                e.getOrder(),
                e.getScheduleId()
        );
        
        // Get the Generated ID of the Exercise and Set it in the Exercise
        ResultSet idResults = DBHandler.query("SELECT LAST_INSERT_ID() FROM exercises");
        try {
            if(!idResults.next()) throw new SQLException("Exercise Creation Failed");
             e.setId(idResults.getInt("LAST_INSERT_ID()"));
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }
    public static ArrayList<Exercise> getExercisesBySchedule(Schedule schedule) {
        // Get All the Exercise from the Schedule's ID
        ResultSet rs = DBHandler.query("SELECT * FROM exercises WHERE schedule_id=?", schedule.getId());
        ArrayList<Exercise> exercises = new ArrayList<>();
        
        // Add the Exercises to A List
        try {
            while (rs.next()) {
                Exercise e = new Exercise();
                
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("name"));
                e.setSets(rs.getInt("sets"));
                e.setReps(rs.getInt("reps"));
                e.setOrder(rs.getInt("e_order"));
                e.setScheduleId(rs.getInt("schedule_id"));
                
                exercises.add(e);
            }
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
        
        // Return Resulted List
        return exercises;
    }
    public static void updateExercise(Exercise e) {
        // Update Database Based on the Exercise's Info
        DBHandler.update("UPDATE exercises SET name=?, sets=?, reps=?, e_order=? WHERE id=?",
                e.getName(),
                e.getSets(),
                e.getReps(),
                e.getOrder(),
                e.getId()
        );
    }
    public static void deleteExercise(int id) {
        // Delete from the Database Using ID
        DBHandler.update("DELETE FROM exercises WHERE id=?", id);
    }
}
