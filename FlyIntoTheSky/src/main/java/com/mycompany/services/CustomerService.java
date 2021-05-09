/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.pojo.Customer;
import com.mycompany.pojo.Ticket;
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
public class CustomerService {
    private Connection conn;
    
    public CustomerService(Connection conn){
        this.conn = conn;
    }
    public Customer getCusById(int id) throws SQLException {
        String sql = "SELECT * from flightdb.customer WHERE id = ?;";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        
        stm.setInt(1, id);
        
        ResultSet rs = stm.executeQuery();
        Customer cus = new Customer();
        while(rs.next()){
            cus.setId(rs.getInt("id"));
            cus.setName(rs.getString("name"));
            cus.setPhone(rs.getString("phone"));
        }

        return cus;

    }
    public int getCusIdByPhone(String p) throws SQLException{
        String sql = "SELECT * from flightdb.customer WHERE phone = ?;";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, p);
        
       ResultSet rs = stm.executeQuery();
       
       if(rs.next())
            return rs.getInt("id");
       return -1;
    }
    
    public boolean addCus(Customer cus) {
        try {
            String sql = "INSERT INTO flightdb.Customer VALUES(?,?,?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setInt(1, cus.getId());
            stm.setString(2, cus.getName());
            stm.setString(3, cus.getPhone());
            
            int rows = stm.executeUpdate();
            
            return rows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return false;
    }
    
    public int createID() throws SQLException {
        String sql = "SELECT * from flightdb.customer;";
        PreparedStatement stm = this.conn.prepareStatement(sql);

        ResultSet rs = stm.executeQuery();
        int count = 0;
        while(rs.next()){
            count++;
        }
        return count + 1;
    }
    
    public String getCusNameById(int id){
        try {
            String sql = "SELECT * from flightdb.customer WHERE id = ?;";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            
            ResultSet rs = stm.executeQuery();
            if(rs.next())
                return rs.getString("name");
        } catch (SQLException ex) {
            Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String getCusPhoneById(int id){
        try {
            String sql = "SELECT * from flightdb.customer WHERE id = ?;";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            
            ResultSet rs = stm.executeQuery();
            if(rs.next())
                return rs.getString("phone");
        } catch (SQLException ex) {
            Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getPhoneByCusId(int id) throws SQLException {
        String q = "SELECT phone from flightdb.customer WHERE id = ?;";
        PreparedStatement stm = this.conn.prepareStatement(q);
        stm.setInt(1, id);
        
        ResultSet rs = stm.executeQuery();
        String phone = new String();
        while (rs.next()) {
            Customer c = new Customer();
            c.setPhone(rs.getString("phone"));
            
            
            
        }
        return phone;
        
           
    }
        
        public List<Ticket> getTicketByCusId(int id) throws SQLException{
        String sql = "SELECT * FROM flightdb.ticket WHERE  customer_id = ?;";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, id);
        
        List<Ticket> tickets = new ArrayList<>();
        ResultSet rs = stm.executeQuery();
        
        while(rs.next()){
            Ticket t = new Ticket();
            t.setId(rs.getInt("id"));
            t.setFlightID(rs.getInt("flight_id"));
            t.setCustomerID(rs.getInt("customer_id"));
            t.setSeatID(rs.getInt("seat_id"));
            t.setPrice(rs.getBigDecimal("price"));
            t.setDateOfIssue(rs.getString("date_of_issue"));
            tickets.add(t);
        }
        return tickets;
    }
}
    

        

