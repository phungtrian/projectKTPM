/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.pojo.Seat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class SeatService {
    private Connection conn;
    
    public SeatService(Connection conn){
        this.conn = conn;
    }
    
    public List<Seat> getSeatsByPlaneID(int id) throws SQLException{
        String sql = "SELECT * FROM flightdb.seat WHERE plane_id = ?;";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        
        stm.setInt(1, id);
        
        ResultSet rs = stm.executeQuery();
        
        List<Seat> seats = new ArrayList<>();
        
        while(rs.next()){
            Seat s = new Seat();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setStatus(rs.getString("status"));
            
            seats.add(s);
        }
        
        return seats;
    }
    
    public Seat getSeatById(int id) throws SQLException{
        String sql = "SELECT * FROM flightdb.seat WHERE id = ?;";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        
        stm.setInt(1, id);
        
        Seat s = new Seat();
        
        ResultSet rs = stm.executeQuery();
        while(rs.next()){
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setStatus(rs.getString("status"));
        }
        return s;
    }
    
    public int checkNullByPlaneId(int id) throws SQLException{
        String sql = "SELECT * FROM flightdb.seat where plane_id = ? AND status = 'Null';";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, id);
        
        ResultSet rs = stm.executeQuery();
        
        while(rs.next()){
            return rs.getInt("id");
        }
        return -1;
    }
    
    public String getSeatNameById(int seatId) {
        try {
            String sql = "SELECT * FROM flightdb.seat WHERE id = ?;";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            
            stm.setInt(1, seatId);
            
            ResultSet rs = stm.executeQuery();
            return rs.getString("name");
        } catch (SQLException ex) {
            Logger.getLogger(SeatService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean changeStatus(int seatId, String status){
        try {
            String sql = "UPDATE flightdb.seat SET status = ? WHERE id = ?;";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setString(1, status);
            stm.setInt(2, seatId);
            
            int row = stm.executeUpdate();
            
            return row > 0;
        } catch (SQLException ex) {
            Logger.getLogger(SeatService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
       public int findSeatIdByStatus(String status) throws SQLException {
            String sql = "SELECT * FROM flightdb.seat WHERE status = ?;";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            
            stm.setString(1, status);  
            
            ResultSet rs = stm.executeQuery();
            if(rs.next())
                return rs.getInt("id");
            return 0;
    }
       
       public boolean setNull(int planeId) {
        try {
            String sql = "UPDATE flightdb.seat SET status = 'Null' WHERE plane_id = ?;";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setInt(1, planeId);
            
            
            int row = stm.executeUpdate();
            
            return row > 0;
        } catch (SQLException ex) {
            Logger.getLogger(SeatService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
       }
}
