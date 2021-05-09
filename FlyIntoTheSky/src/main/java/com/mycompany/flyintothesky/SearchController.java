package com.mycompany.flyintothesky;

import com.mycompany.pojo.Airport;
import com.mycompany.pojo.Flight;
import com.mycompany.pojo.Ticket;
import com.mycompany.services.AirportService;
import com.mycompany.services.FlightService;
import com.mycompany.services.JdbcUtils;
import com.mycompany.services.TicketService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class SearchController implements Initializable{
    @FXML
    private Button btSearch;
    @FXML
    private Button btBack;
    @FXML
    private ComboBox<Airport> cbNoidi;
    @FXML 
    private DatePicker date;
    @FXML
    private ComboBox<Airport> cbNoiden;
    @FXML
    private TableView<Flight> tbFlights;
    
    public static Ticket T;
 
    
    @FXML
    private void seletedDate(ActionEvent event){
        LocalDate toDay = LocalDate.now();
        LocalDate input = date.getValue();
        if(input.isBefore(toDay)){
            Utils.getBox("PLEASE PICK A DAY FROM THE FUTURE!!!", Alert.AlertType.INFORMATION).show();
            date.setValue(toDay);
        }        
    }
    
    @FXML
    private void searchFlight(ActionEvent event) {
        if(cbNoidi.getValue() == null){
            if(cbNoiden.getValue() == null)
                loadData(null, null, date.getValue().toString());
            else if(date.getValue() == null)
                loadData(null, cbNoiden.getValue().toString(), null);
            else
                loadData(cbNoidi.getValue().toString(), cbNoiden.getValue().toString(), null);
        }
        else if(cbNoiden.getValue() == null){
            if( date.getValue() == null)
                loadData(cbNoidi.getValue().toString(), null, null);
            else
                loadData(cbNoidi.getValue().toString(), null, date.getValue().toString());
        }
        else if(date.getValue() == null)
            loadData(cbNoidi.getValue().toString(), cbNoiden.getValue().toString(), null);
        else if(cbNoidi.getValue() == null && cbNoiden.getValue() == null && date.getValue() == null)
            Utils.getBox("PLEASE ENTER THE FLIGHT INFORMATION WHICH YOU WANT TO SEARCH!!!", Alert.AlertType.WARNING).show();
        else
            loadData(cbNoidi.getValue().toString(), cbNoiden.getValue().toString(), date.getValue().toString());

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
                    
                    T = (t.getTicketByID(t.checkNullByFlightId(f.getId())));
                    if(t.checkNullByFlightId(f.getId()) != -1)
                        App.setRoot("addInfo");
                    else
                        Utils.getBox("Tickets are running out liu liu!!!", Alert.AlertType.ERROR).show();
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

    @FXML
    private void switchToTicket(ActionEvent event) throws IOException {
        App.setRoot("ticket");
    }


}
