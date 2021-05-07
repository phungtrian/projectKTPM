/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flyintothesky;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class BookingController implements Initializable {


    @FXML
    private Button btBack;
    @FXML
    private TextField txtPhone;
    @FXML
    private Button btSearch;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void switchToTicket(ActionEvent event) {
    }

}
