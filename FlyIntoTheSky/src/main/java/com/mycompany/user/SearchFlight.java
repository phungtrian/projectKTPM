/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.user;

import com.mycompany.pojo.Flight;
import com.mycompany.services.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phung
 */
public class SearchFlight {
    public List<Flight> FindFlight(String kw) throws SQLException
    {
        Connection conn = JdbcUtils.getConn();
        String sql = "Select * FROM FLIGHT WHERE ID = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, kw);
        ResultSet rs = stm.executeQuery();
        List<Flight> flights = new ArrayList<>();
        while (rs.next()) 
        {
           int id = rs.getInt("id");
           String origin = rs.getString("origin");
           String destination = rs.getString("destination");
           String boardingTime = rs.getString("boardingTime");
           String day = rs.getString("day");
           Flight flight = new Flight(id, origin, destination, boardingTime, day);
           flights.add(flight);
       }
        return flights;
    }
}