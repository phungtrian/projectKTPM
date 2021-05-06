/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.pojo.Ticket;
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
public class TicketService {
    private Connection conn;
    
    public TicketService(Connection conn){
        this.conn = conn;
    }
    
    public Ticket getTicketByID(int id) throws SQLException{
        String sql = "SELECT * FROM flightdb.ticket WHERE  id = ?;";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, id);

        Ticket t = new Ticket();

        ResultSet rs = stm.executeQuery();

        while(rs.next()){
            t.setId(rs.getInt("id"));
            t.setPrice(rs.getBigDecimal("price"));
            t.setAgentID(rs.getInt("agent_id"));
            t.setCustomerId(rs.getInt("customer_id"));
            t.setFlightID(rs.getInt("flight_id"));
            t.setSeatID(rs.getInt("seat_id"));
            t.setStatus(rs.getString("status"));
            t.setDateOfIssue(rs.getString("date_of_issue"));
        }
        return t;
    }
    
    public int getIdByCusId(int id) throws SQLException{
        String sql = "SELECT * FROM flightdb.ticket WHERE  customer_id = ?;";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, id);
        
        ResultSet rs = stm.executeQuery();
        if(rs.next())
            return rs.getInt("id");
        return -1;
    }
    
    
    public int countTicketByFlightId(int id) throws SQLException{
        String sql = "SELECT count(flight_id)  FROM flightdb.ticket WHERE flight_id = ?;";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, id);
        
        ResultSet rs = stm.executeQuery();
        return rs.getInt("count(flight_id)");
        
    }
    
    
    public boolean addTicket(int flightID, int seatID){
        try {
            String sql = "INSERT INTO `flightdb`.`ticket` (`seat_id`, `flight_id`) VALUES (?, ?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            
            stm.setInt(1, seatID);
            stm.setInt(2, flightID);
            int rows = stm.executeUpdate();
            
            return rows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
 
    
    public boolean addCusInfo(int ticketId, int cusID){
        try {
            String sql = "UPDATE `flightdb`.`ticket` SET `customer_id` = ? WHERE id = ?;";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            
            stm.setInt(1, cusID);
            stm.setInt(2, ticketId);
            
            int rows = stm.executeUpdate();
            
            return rows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean changeStatus(int ticketId, String status){
        try {
            String sql = "UPDATE flightdb.ticket SET status = ? WHERE id = ?;";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setString(1, status);
            stm.setInt(2, ticketId);
            
            int row = stm.executeUpdate();
            
            return row > 0;
        } catch (SQLException ex) {
            Logger.getLogger(SeatService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int findTicketIdByStatus(String status){
        try {
            String sql = "SELECT * FROM flightdb.ticket WHERE status = ?;";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            
            stm.setString(1, status);
            
            ResultSet rs = stm.executeQuery();
            if(rs.next())
                return rs.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int checkNullByFlightId(int id){
        try {
            String sql = "SELECT * FROM flightdb.ticket WHERE flight_id = ? AND status = 'Null';";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setInt(1, id);
            
            ResultSet rs = stm.executeQuery();
            
            if(rs.next())
                return rs.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public boolean addDateIssue(int id, String d){
        try {
            String sql = "UPDATE flightdb.ticket SET date_of_issue = ? WHERE id = ?;";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            
            stm.setString(1, d);
            stm.setInt(2, id);
            
            int row = stm.executeUpdate();
            
            return row > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean DeleteTicket(int ticketId){ 
        try {
            Connection conn = JdbcUtils.getConn();
            String sql = "DELETE FROM flightdb.ticket WHERE id = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setInt(1,ticketId);
            
            int row = stm.executeUpdate();
            return row > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
