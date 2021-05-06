/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flyintothesky;

import com.mycompany.pojo.Customer;
import com.mycompany.pojo.Flight;
import com.mycompany.pojo.Seat;
import com.mycompany.pojo.Ticket;
import com.mycompany.services.CustomerService;
import com.mycompany.services.FlightService;
import com.mycompany.services.JdbcUtils;
import com.mycompany.services.SeatService;
import com.mycompany.services.TicketService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import com.mycompany.flyintothesky.SearchController;
/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ConfirmController implements Initializable {
    @FXML
    private Button confirm;
    @FXML
    private Button goBack;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label ori;
    @FXML
    private Label des;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private Label seatName;
    @FXML
    private Label total;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection conn = JdbcUtils.getConn();
            TicketService t = new TicketService(conn);
            CustomerService c = new CustomerService(conn);
            FlightService f = new FlightService(conn);
            SeatService s = new SeatService(conn);

            SearchController search = new SearchController();
            
            Seat seat = s.getSeatById(search.T.getSeatID());
            Flight flight = f.getFlightById(search.T.getFlightID());
            Customer cus = c.getCusById(t.getCusId(search.T.getId()));

            this.name.setText(cus.getName());
            this.phone.setText(cus.getPhone());
            this.ori.setText(flight.getOrigin());
            this.des.setText(flight.getDestination());
            this.date.setText(flight.getDay());
            this.time.setText(flight.getTime());
            this.seatName.setText(seat.getName());
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmController.class.getName()).log(Level.SEVERE, null, ex);        
            }
    }    
    @FXML
    private void confirm(){
        try {
            Connection conn = JdbcUtils.getConn();
            TicketService t = new TicketService(conn);
            CustomerService c = new CustomerService(conn);
            
            SearchController search = new SearchController();
            LocalDate toDay = LocalDate.now();
            
            if( t.addDateIssue(search.T.getId(), toDay.toString())){
                
                if(t.changeStatus(search.T.getId(), "Booked")){
                    
                    Utils.getBox("Do you want to pay?", Alert.AlertType.CONFIRMATION)
                            .showAndWait().ifPresent(bt -> {
                                if (bt == ButtonType.OK) {
                                    try {
                                        App.setRoot("Payment");
                                    } catch (IOException ex) {
                                        Logger.getLogger(ConfirmController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                else
                                    try {
                                        App.setRoot("home");
                                } catch (IOException ex) {
                                    Logger.getLogger(ConfirmController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                }
            }   } catch (SQLException ex) {
            Logger.getLogger(ConfirmController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void cancel() throws IOException{
        try {
            Connection conn = JdbcUtils.getConn();
            TicketService t = new TicketService(conn);
            
            SearchController search = new SearchController();
            
            if(t.changeStatus(search.T.getId(), "Null"))
                App.setRoot("searchFlight_BranchNew");
                
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
