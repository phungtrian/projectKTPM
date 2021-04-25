package com.mycompany.flyintothesky;

import com.mycompany.pojo.Airport;
import com.mycompany.pojo.Flight;
import com.mycompany.services.AirportService;
import com.mycompany.services.JdbcUtils;
import com.mycompany.user.SearchFlight;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = formatter.format(date);
    
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
        loadTable();
        loadData(" ", " ", " ");
        
        this.tbFlights.setRowFactory(obj -> {
            TableRow row = new TableRow();
            
            row.setOnMouseClicked(evt -> {
                try {
                    Flight p = this.tbFlights.getSelectionModel().getSelectedItem();
                    
                    Connection conn = JdbcUtils.getConn();
                    AirportService s = new AirportService(conn);
                    Airport c = s.getAirportById(p.getId());
                    
                    this.cbNoidi.getSelectionModel().select(c);
                    this.cbNoiden.getSelectionModel().select(c);
                    
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            return row;
        });
    }
    private void loadData(String ori, String des, String d) {
        try {
            Connection conn = JdbcUtils.getConn();
            SearchFlight s = new SearchFlight(conn);

            tbFlights.setItems(FXCollections.observableList(s.FindFlightLocation(ori, des, d)));

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTable() {
        TableColumn colFrom = new TableColumn("Nơi đi");
        colFrom.setCellValueFactory(new PropertyValueFactory("origin"));

        TableColumn colTo = new TableColumn("Nơi đến");
        colTo.setCellValueFactory(new PropertyValueFactory("destination"));

        TableColumn colDay = new TableColumn("Ngày bay");
        colDay.setCellValueFactory(new PropertyValueFactory("day"));

        TableColumn colTime = new TableColumn("Thời gian bay");
        colTime.setCellValueFactory(new PropertyValueFactory("time"));

        TableColumn colAction = new TableColumn();
        colAction.setCellFactory(obj -> {
            Button btn = new Button("Chọn");

//            btn.setOnAction((evt) -> {
//                @FXML
//                private void switchToAddInfo() throws IOException{
//                    App.setRoot("addInfo");
//                }
//            });

            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });

        this.tbFlights.getColumns().addAll(colFrom, colTo, colDay, colTime, colAction);
    }

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
//        
