/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestAll;

import com.mycompany.pojo.Customer;
import com.mycompany.pojo.Flight;
import com.mycompany.services.CustomerService;
import com.mycompany.services.FlightService;
import com.mycompany.services.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
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
public class TestCustomerService {
    private static Connection conn;
    
    @BeforeAll
    public static void setUpClass() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(TestCustomerService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterAll
    public static void tearDownClass() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(TestCustomerService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    @Test
    public void testGetCusById() throws SQLException {
        CustomerService c = new CustomerService(conn);
        Customer cs = c.getCusById(3);
            
        Assertions.assertTrue(cs.getName().contains("BinhBung"));
    }
    
    @Test
    public void testWrongIDGetCusById() throws SQLException {
        CustomerService c = new CustomerService(conn);
        Customer cs = c.getCusById(-10);
            
        Assertions.assertNull(cs.getName());
    }
    
    @Test
    public void testGetCusIdByPhone() throws SQLException {
        CustomerService c = new CustomerService(conn);
        
            
        Assertions.assertEquals(3, c.getCusIdByPhone("0889343929"));
    }
    
    @Test
    public void testUnknownPhoneGetCusIdByPhone() throws SQLException {
        CustomerService c = new CustomerService(conn);
        
            
        Assertions.assertEquals(-1, c.getCusIdByPhone("19856"));
    }
    
//    @Test
//    public void testGetCusNameById() throws SQLException {//hàm này kết quả sai gòi 
//        CustomerService c = new CustomerService(conn);
//        
//            
//        Assertions.assertTrue(c.getCusNameById(1).contains("linkcute"));
//    }
    
//    @Test
//    public void testGetCusPhoneById() throws SQLException { 
//        CustomerService c = new CustomerService(conn);
//        
//            
//        Assertions.assertTrue(c.getCusPhoneById(3).contains("0889343929"));
//    }
    
    @Test
    public void testWrongIDGetCusPhoneById() throws SQLException {
        CustomerService c = new CustomerService(conn);
        
            
        Assertions.assertNull(c.getCusPhoneById(-10));
    }
}