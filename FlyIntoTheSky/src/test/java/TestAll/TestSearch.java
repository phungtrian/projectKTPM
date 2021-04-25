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
    public void testWithKeyWord() {
        try {
           //SearchFlight s = new SearchFlight();
           SearchFlight s = new SearchFlight(conn);
            List<Flight> ds = s.FindFlight("1");
            
            ds.forEach(p -> {
                Assertions.assertEquals(p.getId(), 1);
            });
        } catch (SQLException ex) {
            Logger.getLogger(TestSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @Test
    public void testTimeout() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            new SearchFlight(conn).FindFlight("HN");
        });
    }
}
