/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flyintothesky;

import com.mycompany.pojo.Ticket;
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
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class BookingController implements Initializable {


    @FXML
    private Button btBack;
    @FXML
    private TextField txtPhone;
    @FXML
    private Button btSearch;
    @FXML
    private TableView tbFlights;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private void loadData(int cusId) {
        try {
            
            Connection conn = JdbcUtils.getConn();
            TicketService t = new TicketService(conn);
            
            tbFlights.setItems(FXCollections.observableList(t.getTicketByCusId(cusId)));

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTable() {
        TableColumn colID = new TableColumn("ID");
        colID.setCellValueFactory(new PropertyValueFactory("id"));

        
        TableColumn colOri = new TableColumn("Origin");
        colOri.setCellValueFactory(new PropertyValueFactory("origin"));

        TableColumn colDes = new TableColumn("Destination");
        colDes.setCellValueFactory(new PropertyValueFactory("destination"));

        TableColumn  colDay = new TableColumn("Day");
        colDay.setCellValueFactory(new PropertyValueFactory("day"));

        TableColumn colTime = new TableColumn("Time");
        colTime.setCellValueFactory(new PropertyValueFactory("time"));
        
        TableColumn colPrice = new TableColumn("Price");
        colTime.setCellValueFactory(new PropertyValueFactory("price"));
        
        TableColumn colCancel = new TableColumn();
        colCancel.setCellFactory((obj) -> {
            Button btn = new Button("Cancel");
            btn.setOnAction(evt -> {
                Utils.getBox("Are you sure to cancel the ticket?", Alert.AlertType.CONFIRMATION)
                     .showAndWait().ifPresent(bt -> {
                         if (bt == ButtonType.OK) {
                             try {
                                 TableCell cell = (TableCell) ((Button) evt.getSource()).getParent();
                                 Ticket tick = (Ticket) cell.getTableRow().getItem();
                                 
                                 Connection conn = JdbcUtils.getConn();
                                 TicketService t = new TicketService(conn);
                                 
                                 if (t.DeleteTicket(tick.getId())) {
                                     Utils.getBox("SUCCESSFUL", Alert.AlertType.INFORMATION).show();
//                                     loadData(cusId);
                                 } else
                                     Utils.getBox("FAILED", Alert.AlertType.ERROR).show();
                                 
                                 conn.close();
                             } catch (SQLException ex) {
                                 ex.printStackTrace();
                                 Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                     });
            });
            
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        
        this.tbFlights.getColumns().addAll(colID, colOri, colDes, colDay, colPrice, colTime, colCancel);   
    }
    @FXML
    private void searchTicket(ActionEvent event) {
    }
    @FXML
    private void switchToTicket(ActionEvent event) throws IOException {
        App.setRoot("ticket");
    }

}
