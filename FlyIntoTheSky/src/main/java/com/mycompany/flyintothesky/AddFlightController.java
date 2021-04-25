/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flyintothesky;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class AddFlightController {

   @FXML
   private Button back;
   @FXML
   private Button add;
   
   @FXML
    private void switchToManage() throws IOException{
        App.setRoot("manage");
    }
}
