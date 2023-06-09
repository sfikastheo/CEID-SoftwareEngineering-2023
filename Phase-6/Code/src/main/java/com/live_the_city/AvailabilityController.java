package com.live_the_city;

import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AvailabilityController {

    @FXML
    private ChoiceBox<Date> datePicker;

    @FXML
    private Label endDate;

    @FXML
    private Label participantsLbl;

    @FXML
    private TextField personNumField;

    @FXML
    private Label startdateLbl;

    @FXML
    private Label title;


    private static List<Date> dates;
    private static Tour tour;

    @FXML
    private void initialize(){
        startdateLbl.setText(dates.get(0).toString());
        endDate.setText(dates.get(dates.size() - 1).toString());
        participantsLbl.setText(Integer.toString(tour.getGroupsPerDate()));
        title.setText(tour.getTitle());
        title.setAlignment(Pos.CENTER);

        datePicker.setItems(FXCollections.observableArrayList(dates));
    }

    @FXML
    private void chooseDate(MouseEvent e){
        Date date = dates.get(datePicker.getSelectionModel().getSelectedIndex());
        
        checkPersAvail(date);
        checkDiscount();

        showPaymentScreen();
    }

    public void checkPersAvail(Date date){

        String persons = personNumField.getText();
        System.out.println("Date: " + date.toString() + " available for "+ persons+ " participants");
    }

    public void checkDiscount(){
        System.out.println("User is not eligible for discount");
    }

    public void showPaymentScreen(){
        try{
            
            PaymentController.setTour(tour);
            
            Scene scene = new Scene(App.loadFXML("payment"), 1280, 800);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        

            Stage currentStage =  (Stage) title.getScene().getWindow();
            currentStage.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void setDates(List<Date> d){
        dates = d;
    }

    public static void setTour(Tour t){
        tour = t;
    }

  


}
