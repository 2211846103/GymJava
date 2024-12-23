/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itse322;

/**
 *
 * @author Zenjar
 */
public class Exercise implements Comparable<Exercise> {
    // Members
    private int id;
    private String name;
    private int sets;
    private int reps;
    private int order;
    private int scheduleId;

    // Comparable Implementation
    public int compareTo(Exercise other) {
        return this.id - other.id;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getSets() {
        return sets;
    }
    public int getReps() {
        return reps;
    }
    public int getOrder() {
        return order;
    }
    public int getScheduleId() {
        return scheduleId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSets(int sets) {
        this.sets = sets;
    }
    public void setReps(int reps) {
        this.reps = reps;
    }
    public void setOrder(int order) {
        this.order = order;
    }
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
}
