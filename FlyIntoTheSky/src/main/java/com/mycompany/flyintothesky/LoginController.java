
package com.mycompany.flyintothesky;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController{

    @FXML
    private Button login;
    
    @FXML
    private Button goBack;
    
    @FXML
    private TextField user;
    
    @FXML
    private PasswordField pass;
    
    @FXML
    private void switchToManage() throws IOException {
        if(user.getText().trim().equals("root")&& pass.getText().equals("1234"))
        {
            App.setRoot("manage");

        }
        else
        {
            Utils.getBox("LOGIN FAILED!!!", Alert.AlertType.ERROR).show();
       }
        }
    @FXML
    private void switchToHome() throws IOException{
        App.setRoot("home");
    }
    
    
}
