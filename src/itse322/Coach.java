/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itse322;

// Imports
import java.util.Date;

/**
 *
 * @author Zenjar
 */
public class Coach extends User {
    // Members
    private String email;
    private int firstYear;
    private float salary;

    // Getters
    public String getEmail() {
        return email;
    }
    public int getFirstYear() {
        return firstYear;
    }
    public Date getFirstYearDate() {
        long epochTimeInYear = this.firstYear - 1970;
        long milliseconds = epochTimeInYear * 365 * 24 * 60 * 60 * 1000L;
        
        return new Date(milliseconds);
    }
    public float getSalary() {
        return salary;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFirstYear(int firstYear) {
        this.firstYear = firstYear;
    }
    public void setSalary(float salary) {
        this.salary = salary;
    }
}
