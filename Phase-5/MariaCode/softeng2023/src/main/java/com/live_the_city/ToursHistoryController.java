package com.live_the_city;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ToursHistoryController {

    //-------ToursHistory View--------//
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button Back_Button;
    @FXML
    private Button newTour_Button;


    @FXML
    void initialize() {
        //fill the table
    }

    @FXML
    void createTour() throws IOException{
        //show NewTour Window
        App.scene = new Scene(App.loadFXML("NewTour_all"));
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(App.scene);
        secondaryStage.show();

        //close current window
        Stage currentstage = (Stage) newTour_Button.getScene().getWindow();
        currentstage.close();
    }

    //@FXML
    void back2home(){
        /*
        App.scene = new Scene(App.loadFXML("NewTour_all"));
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(App.scene);
        secondaryStage.show();

        Stage currentstage = (Stage) newTour_Button.getScene().getWindow();
        currentstage.close();
         */
    }

}

