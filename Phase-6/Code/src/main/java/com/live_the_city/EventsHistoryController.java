package com.live_the_city;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EventsHistoryController {

    //-------EventsHistory View--------//

    @FXML
    private AnchorPane EventsHistoryView;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button Back_Button;
    @FXML
    private Button newEvent_Button;
    @FXML
    private TableView<Event> EventsTableView;
    @FXML
    private TableColumn<Event, Integer> EventId_Column;
    @FXML 
    private TableColumn<Event, String> Title_Column;
	@FXML 
    private TableColumn<Event, String> Description_Column;
    @FXML 
    private TableColumn<Event, String> DatePublished_Column;
    @FXML 
    private TableColumn<Event, String> DateOfEvent_Column;
    @FXML 
    private TableColumn<Event, String> Rating_Column;

    //-----Other variables----------
    private ObservableList<Event> eventList = FXCollections.observableArrayList();
    
    @FXML
    void initialize() {
        //fill the table
        try{
            Statement stm = DBcommunicator.getConnection().createStatement();
            String query = "select id, title, descr, date_uploaded, event_date, rating from event;";
            ResultSet data = stm.executeQuery(query);

            while (data.next()){
                /*
                Timestamp ts = data.getTimestamp(5);
                //Date dt = new Date(ts.getTime());
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
                String finaldate = formatter.format(ts);
                */
                eventList.add(new Event(data.getInt(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getFloat(6)));                
            }
            data.close();

            EventId_Column.setCellValueFactory(new PropertyValueFactory<>("id"));
            Title_Column.setCellValueFactory(new PropertyValueFactory<>("title"));
            Description_Column.setCellValueFactory(new PropertyValueFactory<>("description"));
			DatePublished_Column.setCellValueFactory(new PropertyValueFactory<>("date_uploaded"));
			DateOfEvent_Column.setCellValueFactory(new PropertyValueFactory<>("date_of_event_string"));
           	Rating_Column.setCellValueFactory(new PropertyValueFactory<>("rating"));    
            EventsTableView.setItems(eventList);
        }catch (SQLException e){
           e.printStackTrace();
        }
    }

    @FXML
    void show() throws IOException{
        //show NewTour Window
        App.scene = new Scene(App.loadFXML("newEventView"));
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(App.scene);
        secondaryStage.show();

        //close current window
        Stage currentstage = (Stage) newEvent_Button.getScene().getWindow();
        currentstage.close();
    }

    @FXML
    void back2home() throws IOException{

        App.scene = new Scene(App.loadFXML("HomePage"));
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(App.scene);
        secondaryStage.show();

        Stage currentstage = (Stage) newEvent_Button.getScene().getWindow();
        currentstage.close();
         
    }
}  
