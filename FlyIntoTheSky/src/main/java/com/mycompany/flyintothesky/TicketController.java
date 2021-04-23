
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
    private void switchToSearch() throws IOException{
        App.setRoot("search");
    }
    
    @FXML
    private void switchToMyBooking() throws IOException{
        App.setRoot("myBooking");
    }
    
    @FXML
    private void switchToHome() throws IOException{
        App.setRoot("home");
    }
}
