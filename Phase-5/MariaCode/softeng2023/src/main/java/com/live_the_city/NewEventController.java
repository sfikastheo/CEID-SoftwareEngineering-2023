package com.live_the_city;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NewEventController {
	
	//-----------------FXML variables-----------------
	@FXML
	private TitledPane newEvent_PostEventView_pane;
	@FXML
	private Button newEvent_postEvent_button;
	@FXML
	private Button newEvent_declineEvent_button;
	@FXML
	private TitledPane newEvent_tagSelectview_pane;
	@FXML
	private ChoiceBox<String> newEvent_tagSelect_choicebox;
	@FXML
	private Button newEvent_continue2_button;
	@FXML 
	private TitledPane newEvent_info_pane;
	@FXML
	private TextField newEvent_title_field;
	@FXML
	private TextArea newEvent_desc_field;
	@FXML 
	private TextArea newEvent_dates_field;
	@FXML
	private TextField newEvent_hour_field;
	@FXML
	private Button newEvent_continue1_button;
	@FXML
	private Label newEvent_ErrorMessage_Label;

	//-----------------General variables-----------------
	private String selectedTags = "";


	//-----------------FXML methods-----------------
	@FXML
	void initialize() {
		// Set visibility of the panes
		//newEvent_CreateEvent_pane.setVisible(true);
		newEvent_info_pane.setVisible(true);
		newEvent_PostEventView_pane.setVisible(false);
		newEvent_tagSelectview_pane.setVisible(false);
	}

	@FXML
	void newEvent_continue1_button_clicked(){
		// Set visibility of the panes
		newEvent_tagSelectview_pane.setVisible(true);
		// load the tags
		load_tags();
	}

	@FXML
	void newEvent_continue2_button_clicked(){
		// Set visibility of the panes
		newEvent_PostEventView_pane.setVisible(true);
		newEvent_tagSelectview_pane.setVisible(false);
		apply_tags();
		validate_form();
	}

	@FXML
	void newEvent_newEvent_declineEvent_button_clicked(){
		// Go back to the Event History view
	}

	@FXML
	void newEvent_postEvent_button_clicked(){
		// Post the event to the database
		// Go back to the Event History view
		
	}

	void apply_tags(){
		// Get the selected tags into a list
		selectedTags = newEvent_tagSelect_choicebox.getValue();
	}

	void validate_form(){
		// Check if all the text fields are filled
		// if not display an error message
		// else display post event view

		// Get the text from the text fields
		String title = newEvent_title_field.getText();
		String desc = newEvent_desc_field.getText();
		String dates = newEvent_dates_field.getText();
		String hour = newEvent_hour_field.getText();

		// Check if the text fields are empty
		if (title.isEmpty() || desc.isEmpty() || dates.isEmpty() || hour.isEmpty()){
			// Display error message
			newEvent_ErrorMessage_Label.setText("Please fill all the fields");
			LabelClearTextTransition(newEvent_ErrorMessage_Label);
		}else{
			// Display post event view
			newEvent_tagSelectview_pane.setVisible(false);
			newEvent_PostEventView_pane.setVisible(true);
		}
	}

	private void load_tags(){
		ObservableList<String> tagnames = FXCollections.observableArrayList();
		String getTags = "Select tagname from Tags;"; 
		try{
			Statement statement = DatabaseConnection.getConnection().createStatement();
			ResultSet tagsSet = statement.executeQuery(getTags);
	
			while (tagsSet.next()){
				tagnames.add(tagsSet.getString(1));
			}
			tagsSet.close();
	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// Initialize the choice box
		newEvent_tagSelect_choicebox.setItems(tagnames);
	}

	private void LabelClearTextTransition(Label errorLabel){
        PauseTransition visibleTextPause = new PauseTransition(
            Duration.seconds(5)
            );
            visibleTextPause.setOnFinished(
                event -> errorLabel.setText("")
            );
            visibleTextPause.play();
    }

}
