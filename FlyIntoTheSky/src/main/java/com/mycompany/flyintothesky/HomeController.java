package com.mycompany.flyintothesky;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class HomeController {
    
    @FXML
    private Button btTicket;
    @FXML
    private Button btLogin;

    
    @FXML
    private void switchToTicket() throws IOException{
        App.setRoot("ticket");
    }
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }
}
