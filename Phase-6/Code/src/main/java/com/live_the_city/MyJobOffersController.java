package com.live_the_city;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MyJobOffersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Back_Button;
    @FXML
    private Button newJobOffer_Button;
    @FXML 
    private TableView<JOffer> MyJobOffers_Table;
    @FXML
    private TableColumn<JOffer, String> DateOpened_Column;
    @FXML
    private TableColumn<JOffer, String> Description_Column;
    @FXML
    private TableColumn<JOffer, String> Expires_Column;
    @FXML
    private TableColumn<JOffer, Integer> NumEmployees_Column;
    @FXML
    private TableColumn<JOffer, String> Public_Column;
    @FXML
    private TableColumn<JOffer, Float> Salary_Column;
    @FXML
    private TableColumn<JOffer, String> Tags_Column;
    @FXML
    private TableColumn<JOffer, String> Title_Column;
    @FXML
    private ImageView back2home;

    private ObservableList<JOffer> jOffersList = FXCollections.observableArrayList();
    
    @FXML
    void initialize() {
        //fill the table
        try{
            Statement stm = DBcommunicator.getConnection().createStatement();
            String query = "select title, descr, date_opened, expires, num_employees, salary, public from joboffer inner join host on employer = hid inner join user on user.id = hid where username=\""+App.current_user+"\";";
            ResultSet data = stm.executeQuery(query);

            while (data.next()){
                jOffersList.add(new JOffer(data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getInt(5), data.getFloat(6), data.getBoolean(7)));                
            }
            data.close();

            //needs testing! Problem with dates and boolean values
            Title_Column.setCellValueFactory(new PropertyValueFactory<>("title"));
            Description_Column.setCellValueFactory(new PropertyValueFactory<>("description"));
            //DateOpened_Column.setCellValueFactory(new PropertyValueFactory<>("date_opened"));
            //Expires_Column.setCellValueFactory(new PropertyValueFactory<>("expires"));
            //NumEmployees_Column.setCellValueFactory(new PropertyValueFactory<>("num_employees"));
            Salary_Column.setCellValueFactory(new PropertyValueFactory<>("salary"));
            Public_Column.setCellValueFactory(new PropertyValueFactory<>("ispublic"));
            MyJobOffers_Table.setItems(jOffersList);

        }catch (SQLException e){
           e.printStackTrace();
        }
    }

    @FXML   //extra functionality out of the use case
    void back2home() throws IOException{
        App.scene = new Scene(App.loadFXML("HomePage"));
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(App.scene);
        secondaryStage.show();

        Stage currentstage = (Stage) newJobOffer_Button.getScene().getWindow();
        currentstage.close();
    }

    @FXML
    void newJOffer(ActionEvent event) throws IOException{
        //show NewTour Window
        App.scene = new Scene(App.loadFXML("NewJOffer"));
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(App.scene);
        secondaryStage.show();

        //close current window
        Stage currentstage = (Stage) newJobOffer_Button.getScene().getWindow();
        currentstage.close();       
    }

}

