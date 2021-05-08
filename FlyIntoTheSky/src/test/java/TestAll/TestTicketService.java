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
    public void testTimeoutGetTicketByID() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            TicketService s = new TicketService(conn);
            Ticket ticket = s.getTicketByID(0);
        });
    }
    
//    @Test
//    public void testGetIdByCusId() {
//        try {
//            TicketService s = new TicketService(conn);
//            
//            Assertions.assertEquals(s.getIdByCusId(2), 81);
//           
//        } catch (SQLException ex) {
//            Logger.getLogger(TestTicketService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    @Test
//    public void testUnknownIDGetIdByCusId() {
//        try {
//            TicketService s = new TicketService(conn);
//            
//            Assertions.assertEquals(s.getIdByCusId(234), -1);
//           
//        } catch (SQLException ex) {
//            Logger.getLogger(TestTicketService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    @Test
//    public void testTimeoutGetIdByCusId() {
//        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
//            TicketService s = new TicketService(conn);
//            int ticket = s.getIdByCusId(0);
//        });
//    }
    
    @Test
    public void testCountTicketByFlightId() {
        try {
            TicketService s = new TicketService(conn);
            
            Assertions.assertEquals(s.countTicketByFlightId(7), 5);//chưa có làm để đây chơi thôi
            
           
        } catch (SQLException ex) {
            Logger.getLogger(TestTicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testNotCorrectIDCountTicketByFlightId() {
        try {
            TicketService s = new TicketService(conn);
            
            Assertions.assertEquals(s.countTicketByFlightId(-100000), 0);
            
           
        } catch (SQLException ex) {
            Logger.getLogger(TestTicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    @Test
//    public void testTrueAddTicket() {
//        TicketService s = new TicketService(conn);
//        Assertions.assertTrue(s.addTicket(1, 10));
//    }
    
//    @Test
//    public void testFalseAddTicket() {
//        TicketService s = new TicketService(conn);
//        
//        Assertions.assertFalse(s.addTicket(100000, 3000));
//    }
    
    
    
    @Test
    public void testTrueAddCusInfo() {
        TicketService s = new TicketService(conn);
        Assertions.assertTrue(s.addCusInfo(82, 1));
    }
    
    @Test
    public void testFalseAddCusInfo() {
        TicketService s = new TicketService(conn);
        Assertions.assertFalse(s.addCusInfo(80000, -10000000));
    }
    
    @Test
    public void testTrueChangeStatus() {
        TicketService s = new TicketService(conn);
        Assertions.assertTrue(s.changeStatus(82, "Booked"));
    }
    
    @Test
    public void testFalseChangeStatus() {
        TicketService s = new TicketService(conn);
        Assertions.assertFalse(s.changeStatus(-100, "Booked"));
    }
    
    @Test
    public void testFindTicketIdByStatus() {
        
        TicketService s = new TicketService(conn);
        Assertions.assertEquals(81, s.findTicketIdByStatus("Booked"));
        
    }
    
    @Test
    public void testFalseFindTicketIdByStatus() {
        
        TicketService s = new TicketService(conn);
        Assertions.assertEquals(-1, s.findTicketIdByStatus("like"));
        
    }
    
    @Test
    public void testCheckNullByFlightId() {
        
        TicketService s = new TicketService(conn);
        Assertions.assertEquals(83, s.checkNullByFlightId(7));
        
    }
}
