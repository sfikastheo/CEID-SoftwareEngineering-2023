package com.live_the_city;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.sql.ResultSet;
import java.sql.Statement;


import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;



public class NewTourController {
//------------ FXML VARIABLES -------------//
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    //--------- New Tour View -----------//
    @FXML
    private AnchorPane NewTourView;
    @FXML 
    private Label location_Label;
    @FXML
    private Label startHour_Label;
    @FXML
    private Label numberOfGroups_Label;
    @FXML
    private Label spotsPerGroup_Label;
    @FXML
    private Button toTagsSelect_Button;
    @FXML
    private TextField title_TextField;
    @FXML
    private TextArea description_TextArea;
    @FXML
    private TextField location_TextField;
    @FXML
    private TextField duration_TextField;
    @FXML
    private TextField groupsNo_TextField;
    @FXML
    private TextField pplingroup_TextField;
    @FXML
    private DatePicker dates_DatePicker;
    @FXML
    private TextField starthour_TextField;
    @FXML
    private Button creategroup_Button;
    @FXML 
    private TextField price_TextField;
    @FXML
    private Label ErrorMessage_Label;
    //-----------TagsSelectView------------//
    @FXML
    private AnchorPane TagsSelectView;
    @FXML
    private Button toFilesUpload_Button;
    @FXML
    private ButtonBar tagsLine1_ButtonBar;
    @FXML
    private ButtonBar tagsLine2_ButtonBar;
    @FXML
    private ButtonBar tagsLine3_ButtonBar;
    @FXML
    private ToggleButton tag1_ToggleButton;
    @FXML
    private ToggleButton tag2_ToggleButton;
    @FXML
    private ToggleButton tag3_ToggleButton;
    @FXML
    private ToggleButton tag4_ToggleButton;
    @FXML
    private ToggleButton tag5_ToggleButton;
    @FXML
    private ToggleButton tag6_ToggleButton;
    @FXML
    private ToggleButton tag7_ToggleButton;
    @FXML
    private ToggleButton tag8_ToggleButton;
    @FXML
    private ToggleButton tag9_ToggleButton;
    @FXML
    private ToggleButton tag10_ToggleButton;
    @FXML
    private ToggleButton tag11_ToggleButton;
    @FXML
    private ToggleButton tag12_ToggleButton;
    @FXML
    private ToggleButton tag13_ToggleButton;
    @FXML
    private ToggleButton tag14_ToggleButton;
    @FXML
    private ToggleButton tag15_ToggleButton;

    //-----------FilesUploadView------------//
    @FXML
    private AnchorPane FilesUploadView;
    @FXML
    private Button toPaymentMethod_Button;
    @FXML
    private Button upload_Button;
    @FXML
    private ListView<String> uploadedfiles_ListView;

    //-----------PaymentMethodView------------//
    @FXML
    private AnchorPane PaymentMethodView;
    @FXML
    private Button toPublish_Button;
    @FXML
    private ToggleButton bankAcc_ToggleButton;
    @FXML
    private ToggleButton paypal_ToggleButton;
    @FXML
    private TextField paymentField1_TextField;
    @FXML
    private TextField paymentField2_TextField;
    @FXML
    private TextField paymentField3_TextField;

    //-----------PublishTourView------------//
    @FXML
    private AnchorPane PublishTourView;
    @FXML
    private Button complete_Button;
    @FXML
    private ToggleButton publish_ToggleButton;
    @FXML
    private ToggleButton save_ToggleButton;
    
//-----------NON FXML VARIABLES----------//
    //prev_view anchor pane to go back
    private AnchorPane current_view;
    private int groups_allowed;
    private int spotsingroup;
    private LocalTime startHour; 
    private int minutes;
    private List<String> groupslist = new ArrayList<String>();
    
//---------NAVIGATION METHODS-----------//
    @FXML
    public void initialize() {
        current_view =  NewTourView;

        NewTourView.setVisible(true);
        TagsSelectView.setVisible(false);
        FilesUploadView.setVisible(false);
        PaymentMethodView.setVisible(false);
        PublishTourView.setVisible(false);
    }

    @FXML
    public void show() throws IOException{
        current_view.setVisible(false);
        switch (current_view.getId()){
            case "NewTourView":
                TagsSelectView.setVisible(true);
                current_view = TagsSelectView;
                break;
            case "TagsSelectView":
                FilesUploadView.setVisible(true);
                current_view = FilesUploadView;
                break;
            case "FilesUploadView":
                PaymentMethodView.setVisible(true);
                current_view = PaymentMethodView;
                break;
            case "PaymentMethodView":
                PublishTourView.setVisible(true);
                current_view = PublishTourView;
                break;
            case "PublishTourView":
                PublishTourView.setVisible(false);
                //open ToursHistory Window
                App.scene = new Scene(App.loadFXML("ToursHistoryView"));
                Stage secondaryStage = new Stage();
                secondaryStage.setScene(App.scene);
                secondaryStage.show();

                //close thiw window
                Stage currentstage = (Stage) complete_Button.getScene().getWindow();
                currentstage.close();
                break; 
        }
    }

//-----------OTHER METHODS-------------//
    @FXML
    public boolean validateTourInfo(ActionEvent event) throws IOException{
        //check for missing data and show label to user
        boolean missing_data = title_TextField.getText().isEmpty() || location_TextField.getText().isEmpty()|| price_TextField.getText().isEmpty() || duration_TextField.getText().isEmpty();
        boolean missing_groups = groupslist.isEmpty(); 

        //check for missing data
        if (missing_data){
            ErrorMessage_Label.setText("There are missing data! Please fill all the fields with the * symbol.");
            LabelClearTextTransition(ErrorMessage_Label);
            return false;
        }
        if (missing_groups) {
            ErrorMessage_Label.setText("You have no groups for your tour! Please insert the number of groups you have chosen or less.");
            LabelClearTextTransition(ErrorMessage_Label);
            return false;
        }

        //check for wrongly formatted data
        try{
            Float.valueOf(price_TextField.getText());
        }catch(Exception e){
            ErrorMessage_Label.setText("Please insert a valid price for your tour.");
            LabelClearTextTransition(ErrorMessage_Label);
            return false;
        }

        try{
            if (Integer.valueOf(duration_TextField.getText())<=0){
                ErrorMessage_Label.setText("Invalid duration for your tour");
                LabelClearTextTransition(ErrorMessage_Label);
                return false;
            }
        }catch(Exception e){
            ErrorMessage_Label.setText("Invalid duration for your tour");
            LabelClearTextTransition(ErrorMessage_Label);
            return false;
        }
    

        show();
        loadTags();
        return true;
    }

    @FXML
    public void addGroup(ActionEvent event){
        LocalDate current_date = dates_DatePicker.getValue();

        //check for missing or invalid date
        if (current_date == null || current_date.isBefore(LocalDate.now())){
            ErrorMessage_Label.setText("Please select a valid date for your group.");
            LabelClearTextTransition(ErrorMessage_Label);
        }else {
            LocalTime endHour = startHour;  //initialize endhour
            //check for wrongly formed or missing data
            try{    //groups_number must be an integer
                groups_allowed = Integer.valueOf(groupsNo_TextField.getText());
            }catch (Exception e){
                ErrorMessage_Label.setText("Invalid groups number.");
                LabelClearTextTransition(ErrorMessage_Label);
                return;
            }

            try{    //spots in group must be an integer
                spotsingroup = Integer.valueOf(pplingroup_TextField.getText());
            }catch (Exception e){
                ErrorMessage_Label.setText("Invalid spots in group number.");
                LabelClearTextTransition(ErrorMessage_Label);
                return;
            }

            try {  //startHour must be in LocalTime format
                startHour = LocalTime.parse(starthour_TextField.getText());
            }
            catch(Exception e){
                ErrorMessage_Label.setText("Invalid start hour. Hours format is hh:mm");
                LabelClearTextTransition(ErrorMessage_Label);
                return;
            }

            try{ //duration must be a positive integer
                minutes = Integer.valueOf(duration_TextField.getText());
                endHour = startHour.plusMinutes(minutes);
                if (!endHour.isAfter(startHour)){
                    ErrorMessage_Label.setText("Invalid duration for your tour.");
                    LabelClearTextTransition(ErrorMessage_Label);
                    return;
                }
            }catch(Exception e){
                ErrorMessage_Label.setText("Duration value incorrectly formed.");
                LabelClearTextTransition(ErrorMessage_Label);
                return;
            }

            Pattern date_regex = Pattern.compile();
            if (!groupslist.isEmpty() && groupslist.contains(current_date+" "+startHour+"-"+endHour)){
                //added an hour that already exists
                ErrorMessage_Label.setText("Group already exists");
                LabelClearTextTransition(ErrorMessage_Label);
            }else if(!groupslist.isEmpty() && Collections.frequency(groupslist, ){
                //reached group number
                ErrorMessage_Label.setText("You have reached the maximum number of groups per date you have chosen");
                LabelClearTextTransition(ErrorMessage_Label);
            }else{  //insert the dates and time available for the tour in a List, disable buttons for new selection of groups and spots
                groupslist.add(current_date.toString()+" "+startHour+"-"+endHour);
                if (!groupsNo_TextField.isDisabled() || !pplingroup_TextField.isDisabled()){
                    duration_TextField.setDisable(true);
                    groupsNo_TextField.setDisable(true);
                    pplingroup_TextField.setDisable(true);
                }
                
            }
            return;
        }     
    }

//--------------NON FXML METHODS--------------//
    private void loadTags(){
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

    tag1_ToggleButton.setText(tagnames.get(0));
    tag2_ToggleButton.setText(tagnames.get(1));
    tag3_ToggleButton.setText(tagnames.get(2));
    tag4_ToggleButton.setText(tagnames.get(3));
    //set the rest after you insert more tags!!
    
    
}

    private void LabelClearTextTransition(Label o){
        PauseTransition visibleTextPause = new PauseTransition(
            Duration.seconds(5)
            );
            visibleTextPause.setOnFinished(
                event -> o.setText("")
            );
            visibleTextPause.play();
    }
    
    private void showMessage(String s){
        ErrorMessage_Label.setText(s);
    }

}
