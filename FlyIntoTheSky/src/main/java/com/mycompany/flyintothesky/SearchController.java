
package com.mycompany.flyintothesky;

import com.mycompany.pojo.Flight;
import com.mycompany.user.SearchFlight;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class SearchController{
    @FXML
    private Button btSearch;

    @FXML
    private TextField txtNoiden;

    @FXML
    private TextField txtNoidi;

    @FXML
    private TextField txtNgaybay;

    @FXML
    private TableView<Flight> tbFlights;
    
    
    @FXML
    private void switchToTicket() throws IOException{
        App.setRoot("ticket");
    }
    static String a;
    public String search(){
        a = txtNoidi.getText();
        a = txtNoidi.getText();
        a = txtNgaybay.getText();
        
        return txtNoidi.getText();

    }
    Boolean flag = false;
    @FXML
    private void showInfo() throws SQLException
    {
        SearchFlight f = new SearchFlight();
        List<Flight> flights = f.FindFlight(search());
        if (f.checkFind(search()))
            flag = true;
        else 
        {
            flag = false;
            Utils.getBox("Không có thông tin chuyến bay mà bạn tra cứu !!!", Alert.AlertType.ERROR).show();
        }
        for(var a : flights)
        {
            
            TableColumn colFrom = new TableColumn("Origin");
            colFrom.setCellValueFactory(new PropertyValueFactory("origin"));

            TableColumn colTo = new TableColumn("Destination");
            colTo.setCellValueFactory(new PropertyValueFactory("destination"));

            TableColumn colTime = new TableColumn("Boarding time");
            colTime.setCellValueFactory(new PropertyValueFactory("boardingTime"));

            TableColumn colDay = new TableColumn("Day");
            colDay.setCellValueFactory(new PropertyValueFactory("day"));

            this.tbFlights.getColumns().addAll(colFrom, colTo, colTime, colDay);
        }
    }
}
