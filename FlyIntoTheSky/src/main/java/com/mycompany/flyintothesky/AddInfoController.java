/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flyintothesky;

import com.mycompany.pojo.Customer;
import com.mycompany.services.CustomerService;
import com.mycompany.services.JdbcUtils;
import com.mycompany.services.SeatService;
import com.mycompany.services.TicketService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AddInfoController{
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private Button btContinue;
    @FXML
    private Button goBack;
    
    
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void switchToSearch() throws IOException{
        App.setRoot("searchFlight_BranchNew");
    }
    @FXML
    private void switchToConfirm() throws IOException{
        
        try {
            Connection conn = JdbcUtils.getConn();
            CustomerService c = new CustomerService(conn);
            TicketService t = new TicketService(conn);
            
            Customer cus = new Customer();
            cus.setName(name.getText());
            cus.setPhone(phone.getText());
            cus.setId(c.createID());
            
            int ticketId = t.findTicketIdByStatus("Booking");
            if(c.addCus(cus) == true ){   
                if(t.addCusInfo(ticketId, cus.getId())){
                    t.changeStatus(ticketId, "Added");
                    App.setRoot("confirm");
                }
            }
            else{
                Utils.getBox("CAN'T ADD INFO!!!", Alert.AlertType.ERROR).show();
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
