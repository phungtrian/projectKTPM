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
import com.mycompany.services.FixflightService;
import com.mycompany.services.JdbcUtils;
import com.mycompany.services.PlaneService;
import com.mycompany.services.TicketService;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Formatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class FixFlightController implements Initializable{
   @FXML
   private Button back;
   
   @FXML private TextField txtId;
   
   @FXML private TextField txtTime;
   
   @FXML
    private Button btSearch;
   
   @FXML
    private Button btUpdate;

    @FXML
    private ComboBox<Airport> cbNoidi;
    
    @FXML
    private ComboBox<Plane> cbPlaneid;

    @FXML private DatePicker date;

    @FXML
    private ComboBox<Airport> cbNoiden;

    @FXML
    private TableView<Flight> tbFlights;

    private static Connection conn;
    
   @FXML
   private void switchToManage() throws IOException{
       App.setRoot("manage");
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
    private void updateFlight(ActionEvent event)  {
       try {
           Flight f = new Flight();
//           SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
//           String dateFormat = formatter.format(date);
           f.setDay(date.getValue().toString());
           f.setOrigin(this.cbNoidi.getSelectionModel().getSelectedItem().getLocation());
           f.setDestination(this.cbNoiden.getSelectionModel().getSelectedItem().getLocation());
           f.setTime(txtTime.getText());
           f.setPlaneId(this.cbPlaneid.getSelectionModel().getSelectedItem().getId());
           
           f.setId(Integer.parseInt(txtId.getText()));
           
           Connection conn = JdbcUtils.getConn();
           FixflightService fs = new FixflightService(conn);
           
           if (fs.updateFlight(f)) {
               Utils.getBox("You have successfully updated the flight!!!", Alert.AlertType.INFORMATION).show();
               loadDataWithoutID(cbNoidi.getSelectionModel().getSelectedItem().getLocation()
                       , cbNoiden.getSelectionModel().getSelectedItem().getLocation()
                       , date.getValue().toString()
                       , txtTime.getText()
                       , cbPlaneid.getSelectionModel().getSelectedItem().getId());
           } else
               Utils.getBox("Update failed!!!!", Alert.AlertType.ERROR).show();
           conn.close();
       } catch (SQLException ex) {
           Logger.getLogger(FixFlightController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection conn = JdbcUtils.getConn();
            AirportService s = new AirportService(conn);

            this.cbNoidi.setItems(FXCollections.observableList(s.getAirports()));
            this.cbNoiden.setItems(FXCollections.observableList(s.getAirports()));
            
            PlaneService ps = new PlaneService(conn);
            this.cbPlaneid.setItems(FXCollections.observableList(ps.getPlanes()));
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FixFlightController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadTable();
        
        this.txtId.textProperty().addListener((obj) -> { 
            loadData2(Integer.valueOf(this.txtId.getText()));
//                , cbNoidi.getSelectionModel().getSelectedItem().getLocation()
//                , cbNoiden.getSelectionModel().getSelectedItem().getLocation()
//                , date.getValue().toString()
//                , txtTime.getText());
        });
        
        this.tbFlights.setRowFactory(obj -> {
            TableRow row = new TableRow();
            
            row.setOnMouseClicked(evt -> {
                try {
                    Flight f = this.tbFlights.getSelectionModel().getSelectedItem();
                    txtId.setText(String.valueOf(f.getId()));
                    txtId.setDisable(true);
//                    String dateFoString = For
//                    Date date1 = Formatter.parse
//                    date.setValue(Formatter.parse());
                    LocalDate Day = LocalDate.parse(f.getDay());
//                    LocalDate input = date.getValue();
                    date.setValue(Day);
                    Connection conn = JdbcUtils.getConn();
//                    FixflightService f2 = new FixflightService(conn);
//                    Flight f3 = f2.getDayFromFlight(f.getDay());
//                    date.setValue(f3);

                    AirportService s = new AirportService(conn);
                    Airport a = s.getAirportByLocate(f.getOrigin());
                    cbNoidi.setValue(a);
                    
                    Airport a2 = s.getAirportByLocate(f.getDestination());
                    cbNoiden.setValue(a2);
                    txtTime.setText(f.getTime());
                  
                    PlaneService ps = new PlaneService(conn);
                    Plane p = ps.getPlaneById(f.getPlaneId());
                    
                    this.cbPlaneid.getSelectionModel().select(p);

                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FixFlightController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            return row;
        });
    }
    
    private void loadData(int id, String ori, String des, String d, String t, int p) {
        try {
            
            Connection conn = JdbcUtils.getConn();
            FixflightService f = new FixflightService(conn);
            
            tbFlights.setItems(FXCollections.observableList(f.FindFlightLocation2(id, ori, des, d, t, p)));

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadData2(int id2) {
       try {
           Connection conn = JdbcUtils.getConn();
           FixflightService f = new FixflightService(conn);
           
           tbFlights.setItems(FXCollections.observableList(f.getFlightByID(id2)));
           
           conn.close();
       } catch (SQLException ex) {
           Logger.getLogger(FixFlightController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    private void loadDataWithoutID(String ori, String des, String d, String t, int p) {
       try {
           Connection conn = JdbcUtils.getConn();
           FixflightService f = new FixflightService(conn);
           
           tbFlights.setItems(FXCollections.observableList(f.upLoadFlightWithoutID(ori, des, d, t, p)));
           
        } catch (SQLException ex) {
           Logger.getLogger(FixFlightController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        TableColumn colPlane = new TableColumn("Plane id");
        colPlane.setCellValueFactory(new PropertyValueFactory("planeId"));
        
        TableColumn colAction = new TableColumn();
        colAction.setCellFactory(obj -> {
            Button btn = new Button("Delete");

            btn.setOnAction((evt) -> {
                Utils.getBox("Are you sure to delete the flight?", Alert.AlertType.CONFIRMATION)
                        .showAndWait().ifPresent(bt -> {
                            if (bt == ButtonType.OK) {
                                try {
                                    TableCell cell = (TableCell) ((Button) evt.getSource()).getParent();
                                    Flight f = (Flight) cell.getTableRow().getItem();

                                    Connection conn = JdbcUtils.getConn();
                                    FixflightService fs = new FixflightService(conn);
                                    if (fs.deleteFlight(f.getId()) == true) {
                                        Utils.getBox("You've successfully deleted the flight!!!", Alert.AlertType.INFORMATION).show();
                                        loadData(Integer.parseInt(txtId.getText())
                                                , cbNoidi.getSelectionModel().getSelectedItem().getLocation()
                                                , cbNoiden.getSelectionModel().getSelectedItem().getLocation()
                                                , date.getValue().toString()
                                                , txtTime.getText()
                                                , cbPlaneid.getSelectionModel().getSelectedItem().getId());
                                    } else {
                                        Utils.getBox("delete failed!!!", Alert.AlertType.ERROR).show();
                                    }
                                } catch (SQLException ex) {
                                    Logger.getLogger(FixFlightController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                });
            });

            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        this.tbFlights.getColumns().addAll(colID, colFrom, colTo, colDay, colTime, colPlane, colAction);

    }
}
