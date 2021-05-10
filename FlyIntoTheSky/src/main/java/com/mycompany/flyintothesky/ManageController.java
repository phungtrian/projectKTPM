/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flyintothesky;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;



public class ManageController {

  @FXML
   private Button add;
  @FXML
   private Button fix;
  @FXML
   private Button confirm;
  @FXML
   private Button back;
    
   @FXML
    private void switchToHome() throws IOException{
        App.setRoot("home");
    }
    
    @FXML
    private void switchToAddFlight() throws IOException{
        App.setRoot("addFlight");
    }
    
    @FXML
    private void switchToFixFlight() throws IOException{
        App.setRoot("fixFlight");
    }
    @FXML
    private void switchToConfirmPayment() throws IOException{
        App.setRoot("paymentConfirmation");
    }
}
