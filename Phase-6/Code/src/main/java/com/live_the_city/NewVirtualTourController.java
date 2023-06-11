package com.live_the_city;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NewVirtualTourController {
	//-----------------FXML variables-----------------
	@FXML
	private AnchorPane new_VirtualTour_View;
	@FXML
	private AnchorPane post_View;
	@FXML
	private AnchorPane tag_Select_View;
	@FXML
	private AnchorPane template_Select_View;
	@FXML
	private AnchorPane alter_VirtualTour_View;
	@FXML
	private ChoiceBox<String> tagSelect_choicebox;
	@FXML
	private ChoiceBox<String> templateSelect_choicebox;
	@FXML
	private TextField title_field;
	@FXML
	private TextArea desc_field;
	@FXML 
	private TextArea dates_field;
	@FXML 
	private TextArea tourInfo_field;
	@FXML
	private TextField cost_field;
	@FXML
	private TextField teamSize_field;
	@FXML
	private TextField bankAccount_field;
	@FXML
	private Button next1_button;
	@FXML
	private Button next2_button;
	@FXML
	private Button next3_button;
	@FXML
	private Button next4_button;
	@FXML
	private Button file_button;
	@FXML
	private Button postTour_button;
	@FXML
	private Button declineTour_button;
	@FXML
	private Label ErrorMessage_Label;

	//-----------------General variables-----------------
	private String selectedTags = "";
	private String Chosen_template = "";
	private String file_path = "";
	private int state = 0;

	@FXML
	void initialize() {
		// Set visibility of the panes
		post_View.setVisible(false);
		tag_Select_View.setVisible(false);
		template_Select_View.setVisible(false);
		new_VirtualTour_View.setVisible(true);

		// Load the tags into the choicebox
		load_tags();
	}

	@FXML
	void show() throws IOException{
		state++;

		switch(state){
			case 1:
				// Show the tag select view
				new_VirtualTour_View.setVisible(false);
				tag_Select_View.setVisible(true);
				break;
			case 2:
				apply_tags();

				// Validate the form
				validate_form();
				break;
			case 4:
				// Show the template select view
				tag_Select_View.setVisible(false);
				template_Select_View.setVisible(true);
				break;
			case 5:
				// Apply the template
				apply_template();

				// Show the post view
				template_Select_View.setVisible(false);
				alter_VirtualTour_View.setVisible(true);
				break;
			case 6:
				// Show the post view
				alter_VirtualTour_View.setVisible(false);
				post_View.setVisible(true);
				break;
			case 7:
				// Go back to the Tour History view
				App.scene = new Scene(App.loadFXML("ToursHistoryView"));
        		Stage secondaryStage = new Stage();
        		secondaryStage.setScene(App.scene);
        		secondaryStage.show();

        		Stage currentstage = (Stage) postTour_button.getScene().getWindow();
        		currentstage.close();
				break;
		}
	}
		
	@FXML
	void post(){
		// Post the event to the database
		//VirtualTour myTour = create_VirtualTour();
		//Store the event in the database
		//store(myTour);

		// Go back to the Tour History view
		try {
			show();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@FXML
    void check_media_size(ActionEvent event) {

        Stage stage = (Stage) file_button.getScene().getWindow();
		ExtensionFilter ex1 = new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(ex1);
				
	    fileChooser.setTitle("Open My File");
				
		fileChooser.setInitialDirectory(new File("/"));
				
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
			System.out.println("Open File");
			System.out.println(selectedFile.getPath());
            
			Long fileSize = selectedFile.length(); // file size in bytes
			if(fileSize < 200000) {
				file_path = selectedFile.getPath();
			}
			else {
				Show_message();
			}
		}
    }

	public VirtualTour create_VirtualTour(){
		// Create a new event
		// Get the text from the text fields
		
		String title = title_field.getText();
		String desc = desc_field.getText();
		String dates = dates_field.getText();
		String teamSize = teamSize_field.getText();
		String cost = cost_field.getText();
		String bankAccount = bankAccount_field.getText();
		String tourInfo = tourInfo_field.getText();
		String tags = selectedTags;
		String template = Chosen_template;
		String media = file_path;

		// Create the VirtualTour object
		VirtualTour myTour = new VirtualTour(title, desc, dates, Integer.parseInt(teamSize), Float.parseFloat(cost), bankAccount, tourInfo, template, media);
		return myTour;
	}

	public void store(VirtualTour myTour){
		// Store the VirtualTour in the database
		
		String query = "INSERT INTO VirtualTour (title, description, location) VALUES myTour.getTitle()+ +myTour.getDescription()+myTour.getLocation()+)";
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

	public void validate_form() throws IOException{
		// Check if all the text fields are filled
		String title = title_field.getText();
		String desc = desc_field.getText();
		String dates = dates_field.getText();
		String teamSize = teamSize_field.getText();
		String cost = cost_field.getText();
		String bankAccount = bankAccount_field.getText();

		if (title.isEmpty() || desc.isEmpty() || dates.isEmpty() || teamSize.isEmpty() || cost.isEmpty() || bankAccount.isEmpty()){
			// Display error message
			Show_message();
			
			// Return to the previous view
			state--;
			tag_Select_View.setVisible(false);
			new_VirtualTour_View.setVisible(true);
		}else{
			// Display post event view
			new_VirtualTour_View.setVisible(false);
			tag_Select_View.setVisible(false);
			template_Select_View.setVisible(true);
			load_templates();
			show();
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

	public void load_templates(){
		// Set a list of templates
		ObservableList<String> templates = FXCollections.observableArrayList();
		templates.add("Cute Template");
		templates.add("Cool Template");
		templates.add("Awesome Template");
		templates.add("Amazing Template");
		templates.add("Incredible Template");
		templates.add("Unbelievable Template");
		templates.add("Fantastic Template");
		templates.add("Fabulous Template");
		templates.add("Marvelous Template");
		templates.add("Wonderful Template");

		// Initialize the choice box
		templateSelect_choicebox.setItems(templates);
	}

	public void apply_template(){
		// Get the selected template
		Chosen_template = templateSelect_choicebox.getValue();
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
