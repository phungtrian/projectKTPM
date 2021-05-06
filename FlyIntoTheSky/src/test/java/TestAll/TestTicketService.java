/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestAll;

import com.mycompany.pojo.Flight;
import com.mycompany.pojo.Ticket;
import com.mycompany.services.FlightService;
import com.mycompany.services.JdbcUtils;
import com.mycompany.services.TicketService;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author kenng
 */
public class TestTicketService {
    
    private static Connection conn;
    
    @BeforeAll
    public static void setUpClass() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(TestTicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterAll
    public static void tearDownClass() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(TestTicketService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Test
    public void testIDGetTicketByID() {
        try {
            TicketService s = new TicketService(conn);
            Ticket tickets = s.getTicketByID(81);
            Assertions.assertEquals(tickets.getId(), 81);
           
        } catch (SQLException ex) {
            Logger.getLogger(TestTicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUnknownIDGetTicketByID() {
        try {
            TicketService s = new TicketService(conn);
            Ticket tickets = s.getTicketByID(-81);
            Assertions.assertEquals(tickets.getId(), 0);
           
        } catch (SQLException ex) {
            Logger.getLogger(TestTicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testTimeout() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            TicketService s = new TicketService(conn);
            Ticket ticket = s.getTicketByID(0);
        });
    }
    
    @Test
    public void testGetIdByCusId() {
        try {
            TicketService s = new TicketService(conn);
            
            Assertions.assertEquals(s.getIdByCusId(2), 81);
           
        } catch (SQLException ex) {
            Logger.getLogger(TestTicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
