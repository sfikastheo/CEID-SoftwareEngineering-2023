package com.live_the_city;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
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

    
    @FXML
    void initialize() {
        
    }

    @FXML
    void back2home(){

    }

    @FXML
    void createJOffer(ActionEvent event) throws IOException{
        //show NewTour Window
        App.scene = new Scene(App.loadFXML("NewTour_all"));
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(App.scene);
        secondaryStage.show();

        //close current window
        Stage currentstage = (Stage) newJobOffer_Button.getScene().getWindow();
        currentstage.close();
       
    }

}

