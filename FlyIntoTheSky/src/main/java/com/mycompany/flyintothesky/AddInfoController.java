/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flyintothesky;

import com.mycompany.pojo.Customer;
import com.mycompany.services.CheckAllTheSituations;
import com.mycompany.services.CustomerService;
import com.mycompany.services.JdbcUtils;
import com.mycompany.services.NameValidation;
import com.mycompany.services.PhoneValidation;
import com.mycompany.services.TicketService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AddInfoController implements Initializable{
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
            NameValidation namevalid = new NameValidation(conn);
            PhoneValidation phonevalid = new PhoneValidation(conn);
            
            Customer cus = new Customer();
            cus.setName(name.getText());
            cus.setPhone(phone.getText());
            if (name.getText() == "")
                Utils.getBox("PLEASE ENTER YOUR FULL NAME!!!", Alert.AlertType.ERROR).show();
            else if (phone.getText() == "")
                Utils.getBox("PLEASE ENTER YOUR PHONE NUMBER!!!", Alert.AlertType.ERROR).show();
            else if (namevalid.validate(name.getText()) == false)
                Utils.getBox("PLEASE ENTER YOUR NAME IN THE FORMAT (FirstName LastName)!!!", Alert.AlertType.ERROR).show();
            else if (phonevalid.validate(phone.getText()) == false)
                Utils.getBox("YOUR PHONE NUMBER IN NOT VALID. PHONE NUMBER MUST HAVE 10 DIGITS!!!", Alert.AlertType.ERROR).show();
            else {
                cus.setId(c.createID());

                SearchController s = new SearchController();
                if(c.addCus(cus) == true) {
    //                SearchController s = new SearchController();
                    if(t.addCusInfo(s.T.getId(), cus.getId()))
                        App.setRoot("confirm"); 
                }
                else
                    Utils.getBox("CAN'T ADD INFO!!!", Alert.AlertType.ERROR).show();
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
