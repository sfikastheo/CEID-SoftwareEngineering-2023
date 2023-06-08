package com.live_the_city;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HomePageController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Label welcome_Label;
    @FXML
    private Button createTour_Button;
    @FXML
    private Button createEvent_Button;
    @FXML
    private Button joinTour_Button;
    @FXML
    private Button joinEvent_Button;
    @FXML
    private Button createVirtualTour_Button;
    @FXML
    private Button createQuiz_Button;
    @FXML
    private Button apply4Job_Button;
    @FXML
    private Button offerJob_Button;
    @FXML
    private Button exit_Button;

    @FXML
    void initialize() {
        welcome_Label.setText("Welcome "+App.current_user);
        switch (App.current_type){
            case "SimpleUser":
                createTour_Button.setDisable(true);
                createEvent_Button.setDisable(true);
                createQuiz_Button.setDisable(true);
                offerJob_Button.setDisable(true);
                createVirtualTour_Button.setDisable(true);
                break;
            case "TourGuide":
                offerJob_Button.setDisable(true);
                joinEvent_Button.setDisable(true);
                joinTour_Button.setDisable(true);
                break;
            case "Host":
            joinTour_Button.setDisable(true);
            joinEvent_Button.setDisable(true);
            apply4Job_Button.setDisable(true);
        }

    }

    @FXML 
    void CreateTourUseCase(ActionEvent event) throws IOException{
        App.scene = new Scene(App.loadFXML("ToursHistoryView"));
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(App.scene);
        secondaryStage.show();

        Stage currentstage = (Stage) createTour_Button.getScene().getWindow();
        currentstage.close();
    }

    @FXML
    void JobOfferUseCase(ActionEvent event) throws IOException{
        App.scene = new Scene(App.loadFXML("MyJobOffers"));
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(App.scene);
        secondaryStage.show();

        Stage currentstage = (Stage) offerJob_Button.getScene().getWindow();
        currentstage.close();
    }

    @FXML
    void exitApp(ActionEvent event){
        System.exit(0);
    }

    @FXML
    void createQuiz_useCase(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newQuizScene.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        NewQuizController nqc  = fxmlLoader.getController();
        nqc.set_nqController(nqc);
        System.out.println(nqc);

        Stage currentstage = (Stage) createQuiz_Button.getScene().getWindow();
        currentstage.close();

    }


    @FXML
    void jobApplication_useCase(ActionEvent event) throws IOException {
        
        App.scene = new Scene(App.loadFXML("jobOfferListView"));
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(App.scene);
        secondaryStage.show();

        Stage currentstage = (Stage) offerJob_Button.getScene().getWindow();
        currentstage.close();

    }

}

