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
public class Client extends User {
    // Members
    private String gender;
    private String tier;
    private Date expiryDate;
    private float height;
    private float weight;
    private int coachId;
    
    // Getters
    public String getGender() {
        return gender;
    }
    public String getTier() {
        return tier;
    }
    public Date getExpiryDate() {
        return expiryDate;
    }
    public float getHeight() {
        return height;
    }
    public float getWeight() {
        return weight;
    }
    public int getCoachId() {
        return coachId;
    }
    
    // Setters
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setTier(String tier) {
        this.tier = tier;
    }
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    public void setExpiryDate(java.sql.Date expiryDateSQL) {
        this.expiryDate = new java.util.Date(expiryDateSQL.getTime());
    }
    public void setHeight(float height) {
        this.height = height;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }
}
