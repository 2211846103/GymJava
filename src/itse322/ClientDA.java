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
public class ClientDA {
    // Methods
    public static void createClient(Client client, String password) {
        // Add Client's Password to the Database
        DBHandler.update("INSERT INTO users_creds (user_type, user_pass) VALUES('client', ?)", password);
        
        // Get Generated ID of the Client
        ResultSet idResults = DBHandler.query("SELECT LAST_INSERT_ID() FROM users_creds");
        try {
            if(!idResults.next()) throw new SQLException("User Creation Failed");
             client.setId(idResults.getInt("LAST_INSERT_ID()"));
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
        
        // Add Client's Data to the Database
        DBHandler.update("INSERT INTO clients VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                new java.sql.Date(client.getBirthDate().getTime()),
                client.getGender(),
                client.getTier(),
                new java.sql.Date(client.getExpiryDate().getTime()),
                client.getHeight(),
                client.getWeight(),
                client.getPhoneNumber(),
                client.getCoachId()
        );
    }
    public static Client getClientById(int id) {
        // Get Results from the Database and Initiate Client Object
        ResultSet rs = DBHandler.query("SELECT * FROM clients WHERE id=?", id);
        Client user = new Client();
        user.setId(id);
        
        // Assign Values from the Database to the Client Object
        try {
            if (!rs.next()) return null;
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setBirthDate(rs.getDate("birth_date"));
            user.setGender(rs.getString("gender"));
            user.setTier(rs.getString("sub_tier"));
            user.setExpiryDate(rs.getDate("expiry_date"));
            user.setHeight(rs.getFloat("height"));
            user.setWeight(rs.getFloat("weight"));
            user.setPhoneNumber(rs.getString("tel"));
            user.setCoachId(rs.getInt("coach_id"));
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
        
        // Return Retrieved User
        return user;
    }
    public static ArrayList<Client> getAllClients() {
        // Get Results from the Database and Initiate ArrayList
        ResultSet rs = DBHandler.query("SELECT * FROM clients");
        ArrayList<Client> users = new ArrayList<>();
        
        // Assign Values from the Database to the Client Object
        try {
            while (rs.next()) {
                Client user = new Client();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setBirthDate(rs.getDate("birth_date"));
                user.setGender(rs.getString("gender"));
                user.setTier(rs.getString("sub_tier"));
                user.setExpiryDate(rs.getDate("expiry_date"));
                user.setHeight(rs.getFloat("height"));
                user.setWeight(rs.getFloat("weight"));
                user.setPhoneNumber(rs.getString("tel"));
                user.setCoachId(rs.getInt("coach_id"));
                
                users.add(user);
            }
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
        
        // Return Retrieved Users
        return users;
    }
    public static ArrayList<Client> getClientsByCoach(Coach coach) {
        // Get Results from the Database and Initiate ArrayList
        ResultSet rs = DBHandler.query("SELECT * FROM clients WHERE coach_id=?", coach.getId());
        ArrayList<Client> clients = new ArrayList<>();
        
        // Assign Values from the Results to the ArrayList
        try {
            while (rs.next()) {
                Client user = new Client();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setBirthDate(rs.getDate("birth_date"));
                user.setGender(rs.getString("gender"));
                user.setTier(rs.getString("sub_tier"));
                user.setExpiryDate(rs.getDate("expiry_date"));
                user.setHeight(rs.getFloat("height"));
                user.setWeight(rs.getFloat("weight"));
                user.setPhoneNumber(rs.getString("tel"));
                user.setCoachId(rs.getInt("coach_id"));
                
                clients.add(user);
            }
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
        
        // Return Retrieved Users
        return clients;
    }
    public static void updateClient(Client client) {
        // Update the Database based on the Id of the Client
        DBHandler.update("UPDATE clients SET first_name=?, last_name=?, birth_date=?, gender=?, sub_tier=?, expiry_date=?, height=?, weight=?, tel=?, coach_id=? WHERE id=?",
                client.getFirstName(),
                client.getLastName(),
                new java.sql.Date(client.getBirthDate().getTime()),
                client.getGender(),
                client.getTier(),
                new java.sql.Date(client.getExpiryDate().getTime()),
                client.getHeight(),
                client.getWeight(),
                client.getPhoneNumber(),
                client.getCoachId(),
                client.getId()
        );
    }
    public static void deleteClient(int id) {
        // Delete Client from the Database
        DBHandler.update("DELETE FROM clients WHERE id=?", id);
    }
}