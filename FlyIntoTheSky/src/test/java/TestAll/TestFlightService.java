/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestAll;

import com.mycompany.pojo.Flight;
import com.mycompany.services.FlightService;
import com.mycompany.services.JdbcUtils;
import java.sql.Connection;
import java.sql.Date;
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
public class TestFlightService {
    
    private static Connection conn;
    
    @BeforeAll
    public static void setUpClass() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(TestFlightService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterAll
    public static void tearDownClass() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(TestFlightService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Test
    public void testGetFlightById() throws SQLException {
        FlightService s = new FlightService(conn);
        Flight f = s.getFlightById(1);
            
        Assertions.assertEquals(f.getId(), 1);
}
    
    
@Test
    public void testUnknowIDGetFlightById() throws SQLException {
        FlightService s = new FlightService(conn);
        Flight f = s.getFlightById(12345);
            
        Assertions.assertNull(f.getOrigin());
}    
//    
    
    

//    
    @Test
    public void testFindFlightLocation() {
        try {
            
           //SearchFlight s = new SearchFlight();
           FlightService s = new FlightService(conn);
            List<Flight> ds = s.FindFlightLocation("HN", "SG", "2021-05-05");
            
            ds.forEach(p -> {
                Assertions.assertTrue(p.getOrigin().contains("HN"));
                Assertions.assertTrue(p.getDestination().contains("SG"));
                Assertions.assertTrue(p.getDay().contains("2021-05-05"));
            });
        } catch (SQLException ex) {
            Logger.getLogger(TestFlightService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
    public void testUnknowOriginFindFlightLocation() {
        try {
            
           //SearchFlight s = new SearchFlight();
           FlightService s = new FlightService(conn);
           List<Flight> ds = s.FindFlightLocation("ABC", "SG", "2021-05-05");
            
           Assertions.assertEquals(0, ds.size());
        } catch (SQLException ex) {
            Logger.getLogger(TestFlightService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testUnknowDestinationFindFlightLocation() {
        try {
            
           //SearchFlight s = new SearchFlight();
           FlightService s = new FlightService(conn);
           List<Flight> ds = s.FindFlightLocation("SG", "NgyenBinh", "2021-05-05");
            
           Assertions.assertEquals(0, ds.size());
        } catch (SQLException ex) {
            Logger.getLogger(TestFlightService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testUnknowDayFindFlightLocation() {
        try {
            
           //SearchFlight s = new SearchFlight();
           FlightService s = new FlightService(conn);
           List<Flight> ds = s.FindFlightLocation("SG", "HN", "4021-05-05");
            
           Assertions.assertEquals(0, ds.size());
        } catch (SQLException ex) {
            Logger.getLogger(TestFlightService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
@Test
    public void testTimeout() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            FlightService s = new FlightService(conn);
            List<Flight> f = s.FindFlightLocation("", "", "2021-05-05");
        });
    }
//    
//    @Test
//    public void testWithKeyWordFindFlightDestination() {
//        try {
//           //SearchFlight s = new SearchFlight();
//           SearchFlight s = new SearchFlight(conn);
//            List<Flight> ds = s.FindFlightDestination("SG");
//            
//            ds.forEach(p -> {
//                Assertions.assertTrue(p.getDestination().contains("SG"));
//            });
//        } catch (SQLException ex) {
//            Logger.getLogger(TestSearch.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    public void testWithKeyWordFindFlightDay() {
//        try {
//           //SearchFlight s = new SearchFlight();
//           SearchFlight s = new SearchFlight(conn);
//            List<Flight> ds = s.FindFlightDay("10/21/2021");
//            
//            ds.forEach(p -> {
//                Assertions.assertTrue(p.getDate().contains("10/12/2021"));
//            });
//        } catch (SQLException ex) {
//            Logger.getLogger(TestSearch.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//     @Test
//    public void testTimeout() {
//        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
//            new SearchFlight(conn).FindFlightID("HN");
//        });
//    }
}
