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
public abstract class User {
    // Members
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String phoneNumber;
    
    // Object Overrides
    public String toString() {
        return firstName + " " + lastName;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public void setBirthDate(java.sql.Date birthDateSQL) {
        this.birthDate = new java.util.Date(birthDateSQL.getTime());
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
