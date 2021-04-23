
package com.mycompany.flyintothesky;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;


public class SearchController{
    @FXML
    private RadioButton oneWay;
    @FXML
    private RadioButton roundTrip;
    @FXML
    private Button goBack;
    
    
    @FXML
    private void switchToTicket() throws IOException{
        App.setRoot("ticket");
    }
    

}
