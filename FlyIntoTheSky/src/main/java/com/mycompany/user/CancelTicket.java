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
public class CancelTicket {
    public static void DeleteTicket(Ticket t) throws SQLException { 
       Connection conn = JdbcUtils.getConn();
       String sql = "DELETE FROM Ticket WHERE id = ?";
       PreparedStatement stm = conn.prepareStatement(sql);
       stm.setInt (1,t.getId());
    }
}
