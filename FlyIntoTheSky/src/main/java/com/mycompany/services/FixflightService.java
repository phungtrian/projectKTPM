/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.pojo.Airport;
import com.mycompany.pojo.Flight;
import com.mycompany.pojo.Plane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
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
public class FixflightService {
    private Connection conn;
   
    public FixflightService(Connection conn){
        this.conn = conn;
    }
    
    public List<Flight> FindFlightLocation2(int id, String ori, String des, String d, String t, int p) throws SQLException
    {
        if (ori == null && des == null && d == null && t == null) {
            throw new SQLDataException();
        }
        String sql = "SELECT * FROM flightdb.flight WHERE id = ? AND origin = ? AND destination = ? AND day = ? AND time = ? AND plane_id = ?;";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, id);
        stm.setString(2, ori);
        stm.setString(3, des);
        stm.setString(4, d);
        stm.setString(5, t);
        stm.setInt(6, p);
        ResultSet rs = stm.executeQuery();
        List<Flight> flights = new ArrayList<>();
        while (rs.next()) 
        {
           Flight f = new Flight();
           f.setId(rs.getInt("id"));
           f.setOrigin(rs.getString("origin"));
           f.setDestination(rs.getString("destination"));
           f.setDay(rs.getString("day"));
           f.setTime(rs.getString("time"));
           f.setPlaneId(rs.getInt("plane_id"));

           flights.add(f);
       }
        return flights;
    }
    
    public List<Flight> upLoadFlightWithoutID(String ori, String des, String d, String t, int  p) throws SQLException
    {
        if (ori == null && des == null && d == null && t == null) {
            throw new SQLDataException();
        }
        String sql = "SELECT * FROM flightdb.flight WHERE origin = ? AND destination = ? AND day = ? AND time = ? AND plane_id = ?;";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, ori);
        stm.setString(2, des);
        stm.setString(3, d);
        stm.setString(4, t);
        stm.setInt(5, p);
        ResultSet rs = stm.executeQuery();
        List<Flight> flights = new ArrayList<>();
        while (rs.next()) 
        {
           Flight f = new Flight();
           f.setId(rs.getInt("id"));
           f.setOrigin(rs.getString("origin"));
           f.setDestination(rs.getString("destination"));
           f.setDay(rs.getString("day"));
           f.setTime(rs.getString("time"));
           f.setPlaneId(rs.getInt("plane_id"));

           flights.add(f);
       }
        return flights;
    }
    
    public List<Flight> getFlightByID(int id) throws SQLException {
        String sql = "SELECT * FROM flightdb.flight WHERE id = ?;";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, id);

        ResultSet rs = stm.executeQuery();

        List<Flight> flights = new ArrayList<>();
        while (rs.next()) {
            Flight f = new Flight();
            f.setId(rs.getInt("id"));
            f.setOrigin(rs.getString("origin"));
            f.setDestination(rs.getString("destination"));
            f.setDay(rs.getString("day"));
            f.setTime(rs.getString("time"));
            f.setPlaneId(rs.getInt("plane_id"));

           flights.add(f);
        }
        return flights;
    }
    
    public boolean deleteFlight(int flightId) {
        try {
            String sql = "DELETE FROM flightdb.flight WHERE id = ?;";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setInt(1, flightId);
            
            int row = stm.executeUpdate();
            
            return row > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FixflightService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean updateFlight(Flight f) throws SQLException {
        String sql = "UPDATE flightdb.flight SET day = ?, origin = ?, destination = ?, time = ?, plane_id = ? WHERE id = ?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, f.getDay());
        stm.setString(2, f.getOrigin());
        stm.setString(3, f.getDestination());
        stm.setString(4, f.getTime());
        stm.setInt(5, f.getPlaneId());
        stm.setInt(6, f.getId());
        
        int row = stm.executeUpdate();
        
        return row > -1;
    }
    
}
