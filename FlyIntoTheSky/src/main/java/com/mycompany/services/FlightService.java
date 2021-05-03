/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.pojo.Customer;
import com.mycompany.pojo.Flight;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class FlightService {
   private Connection conn;
   
    public FlightService(Connection conn){
        this.conn = conn;
    }
        
    public boolean addFilght(Flight f){
       try {
           String sql = "INSERT INTO flightdb.flight VALUES(?, ?, ?, ?, ?, ?);";
           PreparedStatement stm = this.conn.prepareStatement(sql);
           stm.setInt(1, f.getId());
           stm.setString(2, f.getOrigin());
           stm.setString(3, f.getDestination());
           stm.setString(4, f.getDay());
           stm.setString(5, f.getTime());
           stm.setInt(6, f.getPlaneId());
           
           
           int rows = stm.executeUpdate();
           
           return rows > 0;
       } catch (SQLException ex) {
           Logger.getLogger(FlightService.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return false;
    }    
    
    public Flight getFlightById(int id) throws SQLException{ 
        String sql = "SELECT * from flightdb.flight WHERE id = ?;";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        
        stm.setInt(1, id);
        
        ResultSet rs = stm.executeQuery();
        Flight f = new Flight();
        
        while(rs.next()){
            f.setId(rs.getInt("id"));
            f.setOrigin(rs.getString("origin"));
            f.setDestination(rs.getString("destination"));
            f.setDay(rs.getString("day"));
            f.setTime(rs.getString("time"));
            f.setPlaneId(rs.getInt("plane_id"));
        }
        return f;

    }
}
