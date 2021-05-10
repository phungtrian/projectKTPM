/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flyintothesky;

import com.mycompany.pojo.Customer;
import com.mycompany.pojo.Flight;
import com.mycompany.pojo.Seat;
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

/**
 *
 * @author phung
 */
public class TicketDetailsController implements Initializable{
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

            BookingController book = new BookingController();
            
            Seat seat = s.getSeatById(book.T2.getSeatID());
            Flight flight = f.getFlightById(book.T2.getFlightID());
            Customer cus = c.getCusById(t.getCusId(book.T2.getId()));

            this.name.setText(cus.getName());
            this.phone.setText(cus.getPhone());
            this.ori.setText(flight.getOrigin());
            this.des.setText(flight.getDestination());
            this.date.setText(flight.getDay());
            this.time.setText(flight.getTime());
            this.seatName.setText(seat.getName());
            this.total.setText(String.valueOf(book.T2.getPrice() + "VND"));
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketDetailsController.class.getName()).log(Level.SEVERE, null, ex);        
            }
    }    
    
    @FXML
    private void switchToPayment() throws IOException{
        App.setRoot("payment");
    }
    
    @FXML
    private void cancel() throws IOException{
        try {
            Connection conn = JdbcUtils.getConn();
            TicketService t = new TicketService(conn);
            
            BookingController book = new BookingController();
            
//            if(t.changeStatus(book.T2.getId(), "Null"))
                Utils.getBox("ARE YOU SURE TO CANCEL THE TICKET", Alert.AlertType.CONFIRMATION)
                        .showAndWait().ifPresent(bt -> {if (bt == ButtonType.OK)
                                                            try {
                                                                if(t.changeStatus(book.T2.getId(), "Null"))
                                                                    App.setRoot("home");
                        } catch (IOException ex) {
                            Logger.getLogger(TicketDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                                                        else if (bt == ButtonType.NO)
                                                            try {
                                                                App.setRoot("ticketDetails");
                        } catch (IOException ex) {
                            Logger.getLogger(TicketDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                        }});
                
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TicketDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
