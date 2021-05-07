/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.pojo.Airport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phung
 */
public class AirportService {
    private Connection conn;
    
    public AirportService(Connection conn) {
        this.conn = conn;
    }
    
    public List<Airport> getAirports() throws SQLException {
        Statement stm = this.conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM airport");
        
        List<Airport> airports = new ArrayList<>();
        while (rs.next()) {
            Airport a = new Airport();
            a.setId(rs.getInt("id"));
            a.setLocation(rs.getString("location"));
            
            airports.add(a);
        }
        
        return airports;
    }
    
    public String getLocatetById(int flightId){
        try {
            String q = "SELECT * FROM flightdb.airport WHERE id = ?;";
            PreparedStatement stm = this.conn.prepareStatement(q);
            stm.setInt(1, flightId);
            
            ResultSet rs = stm.executeQuery();
            if(rs.next())
                return rs.getString("location");
        } catch (SQLException ex) {
            Logger.getLogger(AirportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int getAirportID(String locat) throws SQLException{
        String q = "SELECT * FROM airport WHERE location = ?";
        PreparedStatement stm = this.conn.prepareStatement(q);
        stm.setString(1, locat);
        ResultSet rs = stm.executeQuery();
        
        return rs.getInt("id");
    }
}
