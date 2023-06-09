package com.live_the_city;

import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class EventsDetController {

    @FXML
    private AnchorPane linkPane;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button linkBtn;

    @FXML
    private TextField linkField;

    @FXML
    private Button attendBtn;

    @FXML
    private Label creatorLbl;

    @FXML
    private ChoiceBox<Date> datePicker;

    @FXML
    private TextArea descField;

    @FXML
    private Label locLbl;

    @FXML
    private Label partLbl;

    @FXML
    private Label ratingLbl;

    @FXML
    private Label titleLbl;

    private static Event event;

    @FXML
    private void initialize(){
        titleLbl.setText(event.getTitle());
        titleLbl.setAlignment(Pos.CENTER);
        creatorLbl.setText("G. Smith");
        descField.setText(event.getDescription());
        locLbl.setText(event.getLocation());
        partLbl.setText(Integer.toString(event.getAttends()));
        ratingLbl.setText(Float.toString(event.getRating()));
        getAvailableDates();
    }

    @FXML
    private void selectDateTime(MouseEvent event) {
        createEventParticipation();
        updateEventStats();
        String link = createInviteLink();
        showInviteLink(link);

    }

    @FXML
    private void closeLink(MouseEvent event) {
        linkPane.setVisible(false);
        mainPane.setVisible(true);
    }

    public void createEventParticipation(){
        User user = new User(1, "zisissour", "123456789", "zis@mail.gr");
        Participation part = new Participation(user, event);

    }
    public void updateEventStats(){
        event.setAttends(event.getAttends()+1);
    }

    public String createInviteLink(){
        String link = "com.live_the_city/"+event.getTitle()+"/"+"zisissour";
        return link;
    }

    public void showInviteLink(String link){
        linkField.setText(link);

        linkPane.setVisible(true);
        mainPane.setVisible(false);
    }

    public void getAvailableDates(){
        ObservableList<Date> dates = FXCollections.observableArrayList();
        dates.add(event.getDate_of_event());

        datePicker.setItems(dates);
    }

    public static void setEvent(Event ev){
        event = ev;
    }

}
