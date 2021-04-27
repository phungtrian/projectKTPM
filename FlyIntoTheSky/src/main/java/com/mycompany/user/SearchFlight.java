/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.user;

import com.mycompany.pojo.Flight;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

/**
 *
 * @author phung
 */
public class SearchFlight {
    private Connection conn;

    public SearchFlight(Connection conn) {
        this.conn = conn;
    }
    
    
//    public List<Flight> FindFlightID(String kw) throws SQLException
//    {
////        Connection conn = JdbcUtils.getConn();
//        String sql = "Select * FROM FLIGHT WHERE ID = ?";
//        PreparedStatement stm = this.conn.prepareStatement(sql);
//        stm.setString(1, kw);
//        ResultSet rs = stm.executeQuery();
//        List<Flight> flights = new ArrayList<>();
//        while (rs.next()) 
//        {
//           int id = rs.getInt("id");
//           String origin = rs.getString("origin");
//           String destination = rs.getString("destination");
//           String boardingTime = rs.getString("boardingTime");
//           String day = rs.getString("day");
//           Flight flight = new Flight(id, origin, destination, boardingTime, day);
//           flights.add(flight);
//       }
//        return flights;
//    }
//    public List<Flight> FindFlightOrigin(String ori) throws SQLException
//    {
////        Connection conn = JdbcUtils.getConn();
//        String sql = "Select * FROM FLIGHT WHERE origin = ?";
//        PreparedStatement stm = this.conn.prepareStatement(sql);
//        stm.setString(1, ori);
//        ResultSet rs = stm.executeQuery();
//        List<Flight> flights = new ArrayList<>();
//        while (rs.next()) 
//        {
//           int id = rs.getInt("id");
//           String origin = rs.getString("origin");
//           String destination = rs.getString("destination");
//           String boardingTime = rs.getString("boardingTime");
//           String day = rs.getString("day");
//           Flight flight = new Flight(id, origin, destination, boardingTime, day);
//           flights.add(flight);
//       }
//        return flights;
//    }
    
//    public List<Flight> FindFlightDestination(String des) throws SQLException
//    {
////        Connection conn = JdbcUtils.getConn();
//        String sql = "Select * FROM FLIGHT WHERE destination = ?";
//        PreparedStatement stm = this.conn.prepareStatement(sql);
//        stm.setString(1, des);
//        ResultSet rs = stm.executeQuery();
//        List<Flight> flights = new ArrayList<>();
//        while (rs.next()) 
//        {
//           int id = rs.getInt("id");
//           String origin = rs.getString("origin");
//           String destination = rs.getString("destination");
//           String boardingTime = rs.getString("boardingTime");
//           String day = rs.getString("day");
//           Flight flight = new Flight(id, origin, destination, boardingTime, day);
//           flights.add(flight);
//       }
//        return flights;
//    }
//    
//    public List<Flight> FindFlightDay(String d) throws SQLException
//    {
////        Connection conn = JdbcUtils.getConn();
//        String sql = "Select * FROM FLIGHT WHERE day = ?";
//        PreparedStatement stm = this.conn.prepareStatement(sql);
//        stm.setString(1, d);
//        ResultSet rs = stm.executeQuery();
//        List<Flight> flights = new ArrayList<>();
//        while (rs.next()) 
//        {
//           int id = rs.getInt("id");
//           String origin = rs.getString("origin");
//           int destination = rs.getString("destination");
//           String boardingTime = rs.getString("boardingTime");
//           String day = rs.getString("day");
//           Flight flight = new Flight(id, origin, destination, boardingTime, day);
//           flights.add(flight);
//       }
//        return flights;
//    }
//    
//    
//    
    public List<Flight> FindFlightLocation(String ori, String des, String d) throws SQLException
    {
        if (ori == null && des == null && d == null ) {
            throw new SQLDataException();
        }
//        Connection conn = JdbcUtils.getConn();
        String sql = "SELECT * FROM flightdb.flight WHERE origin = ? AND destination = ? AND day = ?;";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, ori);
        stm.setString(2, des);
        stm.setString(3, d);
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
//           int id = rs.getInt("id");
//           int origin = rs.getInt("origin");
//           int destination = rs.getInt("destination");
//           String boardingTime = rs.getString("boardingTime");
//           String day = rs.getString("day");
////           Flight flight = new Flight(id, origin, destination, boardingTime, day);
           flights.add(f);
       }
        return flights;
    }
    
//    public boolean checkFind(String kw) throws SQLException
//     {
//         List<Flight> flights = FindFlightID(kw);
//         if (flights.isEmpty()) return false;
//         return true;
//     }
}
