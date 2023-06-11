package com.live_the_city;

import java.io.IOException;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NewEventController {

	//-----------------FXML variables-----------------
	@FXML
	private AnchorPane new_Event_View;
	@FXML
	private AnchorPane post_View;
	@FXML
	private AnchorPane tag_Select_View;
	@FXML
	private ChoiceBox<String> tagSelect_choicebox;
	@FXML
	private TextField title_field;
	@FXML
	private TextField eventLocation_field;
	@FXML
	private TextArea desc_field;
	@FXML 
	private TextArea dates_field;
	@FXML
	private Button next2_button;
	@FXML
	private Button next1_button;
	@FXML
	private Button postEvent_button;
	@FXML
	private Button declineEvent_button;
	@FXML
	private Label ErrorMessage_Label;

	//-----------------General variables-----------------
	private String selectedTags = "";
	private int state = 0;


	@FXML
	void initialize() {
		// Set visibility of the panes
		post_View.setVisible(false);
		tag_Select_View.setVisible(false);
		new_Event_View.setVisible(true);

		// Load the tags into the choicebox
		load_tags();
	}

	@FXML
	void show() throws IOException{
		state++;

		switch(state){
			case 1:
				// Show the tag select view
				new_Event_View.setVisible(false);
				tag_Select_View.setVisible(true);
				break;
			case 2:
				apply_tags();

				// Validate the form
				validate_form();
				break;
			case 3:
				// Go back to the Event History view
				App.scene = new Scene(App.loadFXML("EventsHistoryView"));
        		Stage secondaryStage = new Stage();
        		secondaryStage.setScene(App.scene);
        		secondaryStage.show();

        		Stage currentstage = (Stage) postEvent_button.getScene().getWindow();
        		currentstage.close();
				break;
		}
	}
		
	@FXML
	void post(){
		// Post the event to the database
		Event myevent = create_event();
		// Store the event in the database
		store(myevent);

		// Go back to the Event History view
		try {
			show();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public Event create_event(){
		// Create a new event
		// Get the text from the text fields
		String title = title_field.getText();
		String desc = desc_field.getText();
		String dates = dates_field.getText();
		String location = eventLocation_field.getText(); 

		// Create a new event object
		Event event = new Event(1,title, desc, dates, location, 1); //dummy id
		return event;
	}

	public void store(Event event){
		// Store the event in the database
		
		String query = "INSERT INTO events (title, description, location) VALUES ('"+event.getTitle()+"', '"+event.getDescription()+"', '"+event.getLocation()+"')";
		// try{
		// 	Statement stmt = DBcommunicator.getConnection().createStatement();
		// 	stmt.executeUpdate(query);
		// }catch(Exception e){
		// 	System.out.println(e);
		// }
	}

	public void apply_tags(){
		// Get the selected tags into a list
		selectedTags = tagSelect_choicebox.getValue();
	}

	public void Show_message(){
		// Display an error message	
		ErrorMessage_Label.setText("Please fill all the fields");
		LabelClearTextTransition(ErrorMessage_Label);
	}

	public void validate_form(){
		// Check if all the text fields are filled
		// if not display an error message
		// else display post event view

		// Get the text from the text fields
		String title = title_field.getText();
		String desc = desc_field.getText();
		String dates = dates_field.getText();
		String location = eventLocation_field.getText(); 

		// Check if the text fields are empty
		if (title.isEmpty() || desc.isEmpty() || dates.isEmpty() || location.isEmpty()){
			// Display error message
			Show_message();
			
			// Return to the previous view
			state--;
			tag_Select_View.setVisible(false);
			new_Event_View.setVisible(true);
		}else{
			// Display post event view
			new_Event_View.setVisible(false);
			tag_Select_View.setVisible(false);
			post_View.setVisible(true);
		}
	}

	public void load_tags(){
		ObservableList<String> tagnames = FXCollections.observableArrayList();
		String getTags = "Select tagname from Tags;"; 
		try{
			Statement statement = DBcommunicator.getConnection().createStatement();
			ResultSet tagsSet = statement.executeQuery(getTags);
	
			while (tagsSet.next()){
				tagnames.add(tagsSet.getString(1));
			}
			tagsSet.close();
	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// Initialize the choice box
		tagSelect_choicebox.setItems(tagnames);
	}

	public void LabelClearTextTransition(Label errorLabel){
        PauseTransition visibleTextPause = new PauseTransition(
            Duration.seconds(5)
            );
            visibleTextPause.setOnFinished(
                event -> errorLabel.setText("")
            );
            visibleTextPause.play();
    }

}
