
package com.mycompany.flyintothesky;

import com.mycompany.services.CustomerService;
import com.mycompany.services.JdbcUtils;
import com.mycompany.services.PassWordValidation;
import com.mycompany.services.UserValidation;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
    private void switchToManage() throws IOException, SQLException {
        Connection conn = JdbcUtils.getConn();
        UserValidation uservalid = new UserValidation(conn);
        PassWordValidation passvalid = new PassWordValidation(conn);
        
        if(user.getText().equals("admin")&& pass.getText().equals("12345678"))
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
