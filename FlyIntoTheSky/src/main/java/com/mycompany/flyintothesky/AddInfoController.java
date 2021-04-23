/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flyintothesky;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AddInfoController{

    @FXML
    private Button btContinue;
    @FXML
    private Button goBack;
     
    @FXML
    private void switchToFlights() throws IOException{
        App.setRoot("flights");
    }
    @FXML
    private void switchToConfirm() throws IOException{
        App.setRoot("confirm");
    }
    
}
