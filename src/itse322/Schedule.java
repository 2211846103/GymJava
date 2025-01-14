/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itse322;

// Imports
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 
 */
public class Schedule implements Comparable<Schedule> {
    // Constants
    public static final ArrayList<String> PREDEFINED_ORDER = new ArrayList<>(
            Arrays.asList("saturday", "sunday", "monday", "tuesday", "wednesday", "thursday")
    );
    
    // Members
    private int id;
    private String weekDay;
    private int clientId;
    
    // Methods
    public int getWeekDayOrder() {
        return PREDEFINED_ORDER.indexOf(this.weekDay);
    }
    
    // Object Overrides
    public String toString() {
        return this.weekDay.substring(0, 1).toUpperCase() + this.weekDay.substring(1);
    }
    
    // Comparable Implementation
    public int compareTo(Schedule other) {
        int thisOrder = getWeekDayOrder();
        int otherOrder = other.getWeekDayOrder();
        
        return thisOrder - otherOrder;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getWeekDay() {
        return weekDay;
    }
    public int getClientId() {
        return clientId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
