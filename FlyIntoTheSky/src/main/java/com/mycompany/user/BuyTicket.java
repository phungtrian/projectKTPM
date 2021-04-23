/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.user;

import com.mycompany.pojo.Ticket;
import com.mycompany.services.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author phung
 */
public class BuyTicket {
    public static void BuyTicket(Ticket t,int idSeat) throws SQLException { 
       Connection conn = JdbcUtils.getConn();
       String sql = "INSERT INTO Ticket(flight, price, time, date, customer) "
                    + "VALUES(?, ?, ?, ?, ?, ?)";
       PreparedStatement stm = conn.prepareStatement(sql);
       stm.setInt (1,t.getCb().getId());
       stm.setBigDecimal(2, t.getPrice());
       stm.setString(3, t.getTime());
       stm.setString(4, t.getDate());
       stm.setString(5, t.getKh().getName());
       stm.executeUpdate();
       sql = "UPDATE Seat SET Status = PLACED WHERE id = ?";
       stm = conn.prepareStatement(sql);
       stm.setInt(1,idSeat);
       stm.executeUpdate();
    }
}
