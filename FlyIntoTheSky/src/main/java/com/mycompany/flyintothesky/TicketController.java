
package com.mycompany.flyintothesky;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class TicketController{
    @FXML
    private Button book;
    @FXML
    private Button myBooking;
    @FXML
    private Button goBack;
    
    @FXML
    private void switchToSearchFlight_BranchNew() throws IOException{
        App.setRoot("searchFlight_BranchNew");
    }
    
    @FXML
    private void switchToBooking() throws IOException{
        App.setRoot("booking");
    }
    
    @FXML
    private void switchToHome() throws IOException{
        App.setRoot("home");
    }
}
