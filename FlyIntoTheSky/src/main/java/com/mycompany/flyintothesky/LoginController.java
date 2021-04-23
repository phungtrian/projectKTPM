
package com.mycompany.flyintothesky;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class LoginController{

    @FXML
    private Button login;
    
    @FXML
    private Button goBack;
    
    
//    @FXML
//    private void switchToSearch() throws IOException{
//        App.setRoot("search");
//    }
    @FXML
    private void switchToHome() throws IOException{
        App.setRoot("home");
    }
}
