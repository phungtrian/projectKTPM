/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.user;

import com.mycompany.pojo.Flight;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kenng
 */
public class AddFlight {
    
    private Connection conn;

    public AddFlight(Connection conn) {
        this.conn = conn;
    }
    
    public boolean addFlight(Flight f) {
        try {
            String sql = "INSERT INTO flightdb.flight(id, origin, destination, time, day, planeId) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setInt(1, f.getId());
            stm.setString(2, f.getOrigin());
            stm.setString(3, f.getDestination());
            stm.setString(4, f.getTime());
            stm.setString(5, f.getDay());
            stm.setInt(6, f.getPlaneId());
            

            int rows = stm.executeUpdate();

            return rows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AddFlight.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
}
