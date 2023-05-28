package com.live_the_city;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
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
    private Button quiztourConnBtn;
    @FXML
    private TableView<Tour> toursTableView;

    //-----Other variables----------
    Quiz quiz;
    Tour selectedTour;

   


    @FXML
    void initialize() {
        //fill the table
    }

    
    @FXML 
    void getSelectedTour(MouseEvent event) {
        this.selectedTour = toursTableView.getSelectionModel().getSelectedItem();
        quiztourConnBtn.setDisable(false);

    } // when a tour is selected from the table



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



    @FXML
    void quizTourConnection(ActionEvent event) {
        NewQuizController newQuizCon = new NewQuizController();
        newQuizCon.setSelectedTour(selectedTour); //sends the selected tour to NewQuizController
        Stage stage = (Stage) quiztourConnBtn.getScene().getWindow();
        stage.close(); //closes current view
    
    }


    //-------------------------Other functions-------------------------------
    
    void hide_back_newTour_Btns(){
        Back_Button.setVisible(false);
        newTour_Button.setVisible(false);
        quiztourConnBtn.setVisible(true);
    } //hides buttons when TourHistoryView is used for quiz-tour connection


}  

