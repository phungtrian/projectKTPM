/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flyintothesky;

import com.mycompany.services.JdbcUtils;
import com.mycompany.services.TicketService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class PaymentConfirmationController implements Initializable {

    @FXML
    private Button btBack;
    @FXML
    private TextField ticketId;
    @FXML
    private Button btConfirm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchToManage(ActionEvent event) throws IOException {
        App.setRoot("manage");
    }

    @FXML
    private void confirm(ActionEvent event) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        TicketService t = new TicketService(conn);
        
        if(t.getTicketByID(Integer.parseInt(ticketId.getText())) != null){
            Utils.getBox("Xác nhận thanh toán?", Alert.AlertType.CONFIRMATION).showAndWait().ifPresent(bt ->{
                if(bt == ButtonType.OK){
                    if(t.changeStatus(Integer.parseInt(ticketId.getText()), "Paid")){
                        Utils.getBox("Thanh toán thành công!!", Alert.AlertType.INFORMATION).show();
                    }
                }
            });
        }
    }
    
}
