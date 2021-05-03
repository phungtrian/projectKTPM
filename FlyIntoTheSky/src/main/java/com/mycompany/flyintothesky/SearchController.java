package com.mycompany.flyintothesky;

import com.mycompany.pojo.Airport;
import com.mycompany.pojo.Flight;
import com.mycompany.pojo.Seat;
import com.mycompany.pojo.Ticket;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class SearchController implements Initializable{
    @FXML
    private Button btSearch;

    @FXML
    private ComboBox<Airport> cbNoidi;
    
    @FXML private DatePicker date;
   
    @FXML
    private ComboBox<Airport> cbNoiden;

    @FXML
    private TableView<Flight> tbFlights;
    
    private static Connection conn;
    
    private void switchToTicket() throws IOException{
        App.setRoot("ticket");
    }
    
    @FXML
    private void seletedDate(ActionEvent event){
        LocalDate toDay = LocalDate.now();
        LocalDate input = date.getValue();
        if(input.isBefore(toDay)){
            Utils.getBox("Please pick a day from future!!!", Alert.AlertType.INFORMATION).show();
            date.setValue(toDay);
        }        
    }
    
    @FXML
    private void searchFlight(ActionEvent event) {
        loadData(cbNoidi.getSelectionModel().getSelectedItem().getLocation()
                , cbNoiden.getSelectionModel().getSelectedItem().getLocation()
                , date.getValue().toString());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection conn = JdbcUtils.getConn();
            AirportService s = new AirportService(conn);

            this.cbNoidi.setItems(FXCollections.observableList(s.getAirports()));
            this.cbNoiden.setItems(FXCollections.observableList(s.getAirports()));

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadTable();
                

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
        TableColumn colID = new TableColumn("ID");
        colID.setCellValueFactory(new PropertyValueFactory("id"));

        
        TableColumn colFrom = new TableColumn("Origin");
        colFrom.setCellValueFactory(new PropertyValueFactory("origin"));

        TableColumn colTo = new TableColumn("Destination");
        colTo.setCellValueFactory(new PropertyValueFactory("destination"));

        TableColumn colDay = new TableColumn("Day");
        colDay.setCellValueFactory(new PropertyValueFactory("day"));

        TableColumn colTime = new TableColumn("Time");
        colTime.setCellValueFactory(new PropertyValueFactory("time"));
        
        this.tbFlights.setRowFactory(obj -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(evt -> {
                try {
                    Connection conn = JdbcUtils.getConn();
                    TicketService t = new TicketService(conn);
                    
                    Flight f = this.tbFlights.getSelectionModel().getSelectedItem();
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

        
        this.tbFlights.getColumns().addAll(colID, colFrom, colTo, colDay, colTime);

    }
}
