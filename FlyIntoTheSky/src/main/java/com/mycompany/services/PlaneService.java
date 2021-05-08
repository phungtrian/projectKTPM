/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.pojo.Plane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class PlaneService {
    private Connection conn;
    
    public PlaneService(Connection conn){
        this.conn = conn;
    }
    
    public List<Plane> getPlanes() throws SQLException {
        Statement stm = this.conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM flightdb.plane;");
        
        List<Plane> planes = new ArrayList<>();
        while (rs.next()) {
            Plane p = new Plane();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setSeatTotal(rs.getInt("seat_total"));
            
            planes.add(p);
        }
        
        return planes;
    }
    
    public int getTotalSeatByID(int id) throws SQLException{
        String sql = "SELECT * FROM flightdb.plane WHERE id = ?;";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, id);
        
        ResultSet rs = stm.executeQuery();
        return rs.getInt("seat_total");
    }
    
    
    public Plane getPlaneById(int flightId) throws SQLException {
        String q = "SELECT * FROM flightdb.plane WHERE id=?";
        PreparedStatement stm = this.conn.prepareStatement(q);
        stm.setInt(1, flightId);
        
        ResultSet rs = stm.executeQuery();
        
        Plane p = null;
        
        while (rs.next()) {
            p = new Plane();
            p.setId(rs.getInt("id"));
            break;
        }
        
        return p;
    }
}
