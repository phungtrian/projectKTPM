/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestAll;

import com.mycompany.pojo.Seat;
import com.mycompany.services.JdbcUtils;
import com.mycompany.services.SeatService;
import java.sql.Connection;
import java.sql.SQLException;
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
public class TestSeatService {
    private static Connection conn;
    
    @BeforeAll
    public static void setUpClass() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(TestSeatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterAll
    public static void tearDownClass() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(TestSeatService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Test
    public void testGetSeatById() {
        try {
            SeatService s = new SeatService(conn);
            Seat seats = s.getSeatById(10);
            
            
                Assertions.assertTrue(seats.getName().contains("B1"));
            
        } catch (SQLException ex) {
            Logger.getLogger(TestSeatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testNullGetSeatById() {
        try {
            SeatService s = new SeatService(conn);
            Seat seats = s.getSeatById(-100);
            
            
                Assertions.assertNull(seats.getName());
            
        } catch (SQLException ex) {
            Logger.getLogger(TestSeatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testCheckNullByPlaneId() throws SQLException {
        SeatService s = new SeatService(conn);
        Assertions.assertEquals(10, s.checkNullByPlaneId(1));
        
    }
    
    @Test
    public void testWrongCheckNullByPlaneId() throws SQLException {
        SeatService s = new SeatService(conn);
        Assertions.assertEquals(-1, s.checkNullByPlaneId(-123));
        
    }
    
//    @Test
//    public void testGetSeatNameById() {
//        SeatService s = new SeatService(conn);
//        
//        Assertions.assertTrue(s.getSeatNameById(1).contains("A1"));
//    }
    
//    @Test
//    public void testChangeStatus() throws SQLException { //chạy được rồi ẩn để k đổi dữ liệu
//        SeatService s = new SeatService(conn);
//        Assertions.assertTrue(s.changeStatus(10, "Null"));
//        
//    }
    
    @Test
    public void testFindSeatIdByStatus() {
        
        SeatService s = new SeatService(conn);
       
        try {
            Assertions.assertEquals(1, s.findSeatIdByStatus("Null"));
        } catch (SQLException ex) {
            Logger.getLogger(TestSeatService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
   
    @Test
    public void testWrongFindSeatIdByStatus() throws SQLException {
        
        SeatService s = new SeatService(conn);
       
        Assertions.assertEquals(0, s.findSeatIdByStatus("BinhBung"));
            
        
    }
    
    @Test
    public void testsetNull() throws SQLException {
        
        SeatService s = new SeatService(conn);
       
        Assertions.assertTrue(s.setNull(2));
            
        
    }
   
    @Test
    public void tesWrongIDSetNull() throws SQLException {
        
        SeatService s = new SeatService(conn);
       
        Assertions.assertFalse(s.setNull(-15));
            
        
    }
    
}
