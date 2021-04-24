
package com.mycompany.flyintothesky;

import com.mycompany.pojo.Airport;
import com.mycompany.pojo.Flight;
import com.mycompany.services.AirportService;
import com.mycompany.services.JdbcUtils;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;


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
    
    @FXML
    private void switchToTicket() throws IOException{
        App.setRoot("ticket");
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
//    static String a;
//    public String search(){
//        a = txtNoidi.getText();
//        a = txtNoidi.getText();
//        a = txtNgaybay.getText();
//        
//        return txtNoidi.getText();
//
//    }
//    Boolean flag = false;
//    @FXML
//    private void showInfo() throws SQLException
//    {
//        
//        SearchFlight f = new SearchFlight(conn);
//        List<Flight> flights = f.FindFlight(search());
//        if (f.checkFind(search()))
//            flag = true;
//        else 
//        {
//            flag = false;
//            Utils.getBox("Không có thông tin chuyến bay mà bạn tra cứu !!!", Alert.AlertType.ERROR).show();
//        }
//        for(var a : flights)
//        {
//            
//            TableColumn colFrom = new TableColumn("Origin");
//            colFrom.setCellValueFactory(new PropertyValueFactory("origin"));
//
//            TableColumn colTo = new TableColumn("Destination");
//            colTo.setCellValueFactory(new PropertyValueFactory("destination"));
//
//            TableColumn colTime = new TableColumn("Boarding time");
//            colTime.setCellValueFactory(new PropertyValueFactory("boardingTime"));
//
//            TableColumn colDay = new TableColumn("Day");
//            colDay.setCellValueFactory(new PropertyValueFactory("day"));
//
//            this.tbFlights.getColumns().addAll(colFrom, colTo, colTime, colDay);
//        }
    }
}
