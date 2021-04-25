/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestAll;

import com.mycompany.pojo.Flight;
import com.mycompany.services.JdbcUtils;
import com.mycompany.user.SearchFlight;
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
public class TestSearch {
    
    private static Connection conn;
    
    @BeforeAll
    public static void setUpClass() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(TestSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterAll
    public static void tearDownClass() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(TestSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Test
    public void testWithKeyWordFindFlightID() {
        try {
           //SearchFlight s = new SearchFlight();
           SearchFlight s = new SearchFlight(conn);
            List<Flight> ds = s.FindFlightID("1");
            
            ds.forEach(p -> {
                Assertions.assertEquals(p.getId(), 1);
            });
        } catch (SQLException ex) {
            Logger.getLogger(TestSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testWithKeyWordFindFlightOrigin() {
        try {
           //SearchFlight s = new SearchFlight();
           SearchFlight s = new SearchFlight(conn);
            List<Flight> ds = s.FindFlightOrigin("HN");
            
            ds.forEach(p -> {
                Assertions.assertTrue(p.getOrigin().contains("HN"));
            });
        } catch (SQLException ex) {
            Logger.getLogger(TestSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testWithKeyWordFindFlightDestination() {
        try {
           //SearchFlight s = new SearchFlight();
           SearchFlight s = new SearchFlight(conn);
            List<Flight> ds = s.FindFlightDestination("SG");
            
            ds.forEach(p -> {
                Assertions.assertTrue(p.getDestination().contains("SG"));
            });
        } catch (SQLException ex) {
            Logger.getLogger(TestSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void testWithKeyWordFindFlightDay() {
        try {
           //SearchFlight s = new SearchFlight();
           SearchFlight s = new SearchFlight(conn);
            List<Flight> ds = s.FindFlightDay("10/21/2021");
            
            ds.forEach(p -> {
                Assertions.assertTrue(p.getDate().toLowerCase().contains("10/12/2021"));
            });
        } catch (SQLException ex) {
            Logger.getLogger(TestSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @Test
    public void testTimeout() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            new SearchFlight(conn).FindFlightID("HN");
        });
    }
}
