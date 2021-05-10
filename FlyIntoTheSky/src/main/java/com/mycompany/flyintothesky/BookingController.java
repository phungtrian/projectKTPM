/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flyintothesky;

import com.mycompany.pojo.Ticket;
import com.mycompany.services.CustomerService;
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
    private TableView tbTickets;
    
    public static Ticket T2;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void searchTicket(ActionEvent event) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        CustomerService cus = new CustomerService(conn);
        if (txtPhone.getText() == "")
            Utils.getBox("Please enter your booking phone number!!!", Alert.AlertType.ERROR).show();
        else if (cus.getCusIdByPhone(this.txtPhone.getText()) != -1)
            loadData(cus.getCusIdByPhone(this.txtPhone.getText()));
        else
            Utils.getBox("There  no tickets are booked by this phone number!!!", Alert.AlertType.ERROR).show();
    }
    
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
        
//        this.txtPhone.textProperty().addListener((obj) -> {});
        loadTable(); 
    }   
    private void loadData(int id) {
        try {
            
            Connection conn = JdbcUtils.getConn();
            TicketService t = new TicketService(conn);
            CustomerService cs = new CustomerService(conn);
            
            cs.getCusIdByPhone(String.valueOf(id));
            
            tbTickets.setItems(FXCollections.observableList(t.getTicketByCusId(id)));

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    private void loadTable() {
        
        TableColumn colID = new TableColumn("Ticket ID");
        colID.setCellValueFactory(new PropertyValueFactory("id"));

        TableColumn colDayOf = new TableColumn("Day of issue");
        colDayOf.setCellValueFactory(new PropertyValueFactory("dateOfIssue"));

        TableColumn colPrice = new TableColumn("Total price");
        colPrice.setCellValueFactory(new PropertyValueFactory("price"));
        
        TableColumn colCusid = new TableColumn("Customer ID");
        colCusid.setCellValueFactory(new PropertyValueFactory("customerId"));

        TableColumn colFlightid = new TableColumn("Flight ID");
        colFlightid.setCellValueFactory(new PropertyValueFactory("flightID"));

        TableColumn colAction = new TableColumn();
        colAction.setCellFactory(obj -> {
            Button btn = new Button("Ticket details");

            btn.setOnAction((evt) -> {
                try {
                    Connection conn = JdbcUtils.getConn();
                    TicketService ts = new TicketService(conn);
                    TableCell cell = (TableCell) ((Button) evt.getSource()).getParent();
                    Ticket t = (Ticket) cell.getTableRow().getItem();
                    T2 = t;
                    Utils.getBox(String.valueOf(T2.getId()), Alert.AlertType.CONFIRMATION).show();
                    App.setRoot("ticketDetails");
                } catch (IOException ex) {
                    Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
        TableCell cell = new TableCell();
        cell.setGraphic(btn);
        return cell;
        
        });
        this.tbTickets.getColumns().addAll(colID, colDayOf, colPrice, colCusid, colFlightid, colAction);   
    }
    
    @FXML
    private void switchToTicket(ActionEvent event) throws IOException {
        App.setRoot("ticket");
    }
}
//        TableColumn colCancel = new TableColumn();
//        colCancel.setCellFactory((obj) -> {
//            Button btn = new Button("Cancel");
//            btn.setOnAction(evt -> {
//                Utils.getBox("Are you sure to cancel the ticket?", Alert.AlertType.CONFIRMATION)
//                     .showAndWait().ifPresent(bt -> {
//                         if (bt == ButtonType.OK) {
//                             try {
//                                 TableCell cell = (TableCell) ((Button) evt.getSource()).getParent();
//                                 Ticket tick = (Ticket) cell.getTableRow().getItem();
//                                 
//                                 Connection conn = JdbcUtils.getConn();
//                                 TicketService t = new TicketService(conn);
//                                 
//                                 if (t.CancelTicket(0)Ticket(tick.getId()) {
//                                     Utils.getBox("SUCCESSFUL", Alert.AlertType.INFORMATION).show();
////                                     loadData(cusId);
//                                 } else
//                                     Utils.getBox("FAILED", Alert.AlertType.ERROR).show();
//                                 
//                                 conn.close();
//                             } catch (SQLException ex) {
//                                 ex.printStackTrace();
//                                 Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
//                             }
//                         }
//                     });
//            });
//            
//            TableCell cell = new TableCell();
//            cell.setGraphic(btn);
//            return cell;
//        });
        
