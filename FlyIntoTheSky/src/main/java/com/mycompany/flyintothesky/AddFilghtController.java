/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flyintothesky;

import com.mycompany.pojo.Airport;
import com.mycompany.pojo.Flight;
import com.mycompany.pojo.Plane;
import com.mycompany.services.AirportService;
import com.mycompany.services.FlightService;
import com.mycompany.services.JdbcUtils;
import com.mycompany.services.PlaneService;
import com.mycompany.services.SeatService;
import com.mycompany.services.TicketService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AddFilghtController implements Initializable {
   @FXML
   private Button back;
   @FXML
   private Button add;
   @FXML
   private TextField id;
   @FXML
   private TextField time;
   @FXML
   private ComboBox<Airport> cbOrigin; 
   @FXML
   private ComboBox<Airport> cbDestination;  
  @FXML
   private ComboBox<Plane> cbPlane;
  @FXML
   private DatePicker date;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection conn = JdbcUtils.getConn();
            AirportService s = new AirportService(conn);
            PlaneService p = new PlaneService(conn); 

            this.cbOrigin.setItems(FXCollections.observableList(s.getAirports()));
            this.cbDestination.setItems(FXCollections.observableList(s.getAirports()));
            this.cbPlane.setItems(FXCollections.observableList(p.getPlanes()));
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    private void switchToManage() throws IOException{
        App.setRoot("manage");
    }
    @FXML
    private void addFlight() throws IOException{
       try {
           Connection conn = JdbcUtils.getConn();
           FlightService f = new FlightService(conn);
           TicketService t = new TicketService(conn);
           SeatService s = new SeatService(conn);
           
           Flight fl = new Flight();
           fl.setId(Integer.parseInt(id.getText()));
           fl.setPlaneId(Integer.parseInt(cbPlane.getValue().toString()));
           fl.setOrigin(cbOrigin.getValue().toString());
           fl.setDestination(cbDestination.getValue().toString());
           fl.setDay(date.getValue().toString());
           fl.setTime(time.getText());
           if(f.addFilght(fl))
           {
               int seatId = s.checkNullByPlaneId(fl.getPlaneId());
               while(seatId != -1){
                   t.addTicket(fl.getId(), seatId);
                   s.changeStatus(seatId, "Creating");
                   seatId = s.checkNullByPlaneId(fl.getPlaneId());
               }
               if(s.setNull(fl.getPlaneId()))
                    Utils.getBox("SUCCESSFUL!!!", Alert.AlertType.INFORMATION).show();
           }
           else
               Utils.getBox("CAN NOT CREATE FLIGHT!!!", Alert.AlertType.ERROR).show();
           conn.close();
       } catch (SQLException ex) {
           Logger.getLogger(AddFilghtController.class.getName()).log(Level.SEVERE, null, ex);
       }
       } 
}