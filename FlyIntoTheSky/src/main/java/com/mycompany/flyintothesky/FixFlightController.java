/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flyintothesky;

import com.mycompany.pojo.Flight;
import com.mycompany.services.AirportService;
import com.mycompany.services.FlightService;
import com.mycompany.services.JdbcUtils;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;




public class FixFlightController implements Initializable{
   @FXML
   private Button back;
   @FXML
   private TextField txtId;
   @FXML
   private ComboBox cbOri;
   @FXML
   private ComboBox cbDes;
   @FXML
   private DatePicker dpDate;
   @FXML
   private TableView tbFlights;
   @FXML
   private TableColumn colId;
   @FXML
   private TableColumn colOri;
   @FXML
   private TableColumn colDes;
   @FXML
   private TableColumn colDay;
   @FXML
   private TableColumn colTime;
   @FXML
   private TableColumn colDelete;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           Connection conn = JdbcUtils.getConn();
           AirportService s = new AirportService(conn);
           
           this.cbOri.setItems(FXCollections.observableList(s.getAirports()));
           this.cbDes.setItems(FXCollections.observableList(s.getAirports()));

           this.tbFlights.setRowFactory(obj -> {
               TableRow row = new TableRow();
               row.setOnMouseClicked(evt -> {
                   try {
                       TicketService t = new TicketService(conn);
                       
                       Flight f = (Flight) this.tbFlights.getSelectionModel().getSelectedItem();
                       int ticketId = t.checkNullByFlightId(f.getId());
                       
                       if(t.changeStatus(ticketId, "Booking"))
                       {
                           
                           App.setRoot("addInfo");
                       }
                       else
                           Utils.getBox("Het ve goi liu liu!!!", Alert.AlertType.ERROR).show();
                       
                   }  catch (IOException ex) {
                       Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                   }
               });
               return row;
           });
           conn.close();
       } catch (SQLException ex) {
           Logger.getLogger(FixFlightController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }    
    private void loadData(String ori, String des, String d) {
        try {
            
            Connection conn = JdbcUtils.getConn();
            FlightService f = new FlightService(conn);
            
            tbFlights.setItems(FXCollections.observableList(f.FindFlightLocation(ori, des, d)));

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadTable() {
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colOri.setCellValueFactory(new PropertyValueFactory("origin"));
        colDes.setCellValueFactory(new PropertyValueFactory("destination"));
        colDay.setCellValueFactory(new PropertyValueFactory("day"));
        colTime.setCellValueFactory(new PropertyValueFactory("time"));
        
        this.tbFlights.setRowFactory(obj -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(evt -> {
                try {
                    Connection conn = JdbcUtils.getConn();
                    TicketService t = new TicketService(conn);
                    
                    Flight f = (Flight) this.tbFlights.getSelectionModel().getSelectedItem();
                    int ticketId = t.checkNullByFlightId(f.getId());

                    if(t.changeStatus(ticketId, "Booking"))
                    {
                        
                        App.setRoot("addInfo");
                    }
                    else
                        Utils.getBox("Het ve goi liu liu!!!", Alert.AlertType.ERROR).show();
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
                });
                return row;
        });

        
        this.tbFlights.getColumns().addAll(colId, colOri, colDes, colDay, colTime);

    }

   @FXML
   private void switchToManage() throws IOException{
       App.setRoot("manage");
   } 


}
