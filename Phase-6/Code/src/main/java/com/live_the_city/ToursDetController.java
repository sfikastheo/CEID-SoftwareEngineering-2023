package com.live_the_city;

import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class ToursDetController {

    @FXML
    private TextArea descField = new TextArea();

    @FXML
    private Label costLbl = new Label();

    @FXML
    private Label durLbl = new Label();

    @FXML
    private Label partLbl = new Label();

    @FXML
    private Label ratingLbl = new Label();

    @FXML
    private Label tguideLbl = new Label();

    @FXML
    private Label tourTitle = new Label();

    @FXML
    private Label virtualLbl = new Label();

    @FXML
    private Label locLbl = new Label();

    @FXML
    private Button joinBtn;

    private static String tour_id;
    private static Tour tour;



    @FXML
    private void initialize() {
        setTour();

        descField.setText(tour.getDescription());
        costLbl.setText(Float.toString(tour.getPrice()));
        durLbl.setText(Integer.toString(tour.getDuration()));
        partLbl.setText(Integer.toString(tour.getTimesBought()));
        locLbl.setText(tour.getLocation());
        ratingLbl.setText(Float.toString(tour.getRating()));
        tguideLbl.setText(tour.getTourGuide().getUsername());
        virtualLbl.setText(Boolean.toString(tour.getVirtual()));
        tourTitle.setText(tour.getTitle());
        tourTitle.setAlignment(Pos.CENTER);

    }


    @FXML
    private void confirmParticipation(MouseEvent event) {
        List <Date> dates = loadDates();
        showDates(dates);
    }

    private void showDates(List<Date> dates) {
            try{
                AvailabilityController.setDates(dates);
                AvailabilityController.setTour(tour);
                
                Scene scene = new Scene(App.loadFXML("availability"), 1280, 800);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            

                Stage currentStage =  (Stage) tourTitle.getScene().getWindow();
                currentStage.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
    }


    private List<Date> loadDates(){
        return tour.getDatesAvailable();
    }

    public static void setTourId(String tourId) {
        tour_id = tourId;
    }

    public static void setTour(){
        TourGuide tourGuide = null;

        try{
            Connection connection = DBcommunicator.getConnection();
            PreparedStatement statement = connection.prepareStatement("select user.id, username, upass, email, iban from user, tourguide JOIN tour ON tour.offered_by = tourguide.tgid WHERE user.id = ?;");
            statement.setString(1,tour_id);

            ResultSet rs = statement.executeQuery();
            rs.next();

            tourGuide = new TourGuide(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));

            //connection.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }

        try{
            Connection conn = DBcommunicator.getConnection();

            PreparedStatement stat = conn.prepareStatement("select * from tour where id = ?");
            stat.setString(1, tour_id);

            ResultSet result = stat.executeQuery();
            result.next();

            List<String> tags = new ArrayList<String>();
            List<Date> dates = new ArrayList<Date>();
        
            for (int i = 0; i<5; i++){
                LocalDate date = LocalDate.now().plusDays(i);
                dates.add(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }

            tour = new Tour(result.getInt(1), result.getString(2),tags, result.getBoolean(3), tourGuide, result.getString(4), result.getInt(5), result.getBoolean(9), result.getDate(10), dates, result.getInt(7), result.getInt(8), result.getString(6), result.getFloat(11), result.getFloat(12), result.getInt(13));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
