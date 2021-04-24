
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
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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

        
        this.tbFlights.setRowFactory(obj -> {
            TableRow row = new TableRow();
            
            row.setOnMouseClicked(evt -> {
                try {
                    Flight p = this.tbFlights.getSelectionModel().getSelectedItem();
                    txtName.setText(p.getName());
                    txtPrice.setText(p.getPrice().toString());
                    
                    
                    Connection conn = JdbcUtils.getConn();
                    AirportService s = new AirportService(conn);
                    Airport c = s.getAirportById(p.getId());
                    
                    this.cbNoidi.getSelectionModel().select(c);
                    
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            return row;
        });
    }
    private void loadData(String ori, String des, Date d) {
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

            btn.setOnAction((evt) -> {
                Utils.getBox("Ban chac chan xoa khong?", Alert.AlertType.CONFIRMATION)
                        .showAndWait().ifPresent(bt -> {
                            if (bt == ButtonType.OK) {
                                try {
                                    TableCell cell = (TableCell) ((Button) evt.getSource()).getParent();
                                    Product p = (Product) cell.getTableRow().getItem();

                                    Connection conn = JdbcUitls.getConn();
                                    ProductService s = new ProductService(conn);
                                    if (s.deleteProduct(p.getId()) == true) {
                                        Utils.getBox("SUCCESSFUL", Alert.AlertType.INFORMATION).show();
                                        loadData("");
                                    } else {
                                        Utils.getBox("FAILED", Alert.AlertType.ERROR).show();
                                    }
                                } catch (SQLException ex) {
                                    Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });

            });

            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });

        this.tbProducts.getColumns().addAll(colId, colName, colDes, colPrice, colAction);
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
