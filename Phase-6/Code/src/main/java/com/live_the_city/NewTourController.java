package com.live_the_city;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
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
    @FXML
    private Label invalidFsize_Label;

    //-----------PaymentMethodView------------//
    @FXML
    private AnchorPane PaymentMethodView;
    @FXML
    private Button toPublish_Button;
    @FXML
    private Button bankAcc_Button;
    @FXML
    private Button paypal_Button;
    @FXML
    private Label help_Label;
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
    private List<ToggleButton> buttons = new ArrayList<ToggleButton>();
    private String s;
    private Tour creation = new Tour();
    
//---------NAVIGATION METHODS-----------//
    @FXML
    void initialize() {
        //Views setup
        current_view =  NewTourView;

        NewTourView.setVisible(true);
        TagsSelectView.setVisible(false);
        FilesUploadView.setVisible(false);
        PaymentMethodView.setVisible(false);
        PublishTourView.setVisible(false);

        //tags buttons list initialization
        buttons.add(tag1_ToggleButton);
        buttons.add(tag2_ToggleButton);
        buttons.add(tag3_ToggleButton);
        buttons.add(tag4_ToggleButton);
        buttons.add(tag5_ToggleButton);
        buttons.add(tag6_ToggleButton);
        buttons.add(tag7_ToggleButton);
        buttons.add(tag8_ToggleButton);
        buttons.add(tag9_ToggleButton);
        buttons.add(tag10_ToggleButton);
        buttons.add(tag11_ToggleButton);
        buttons.add(tag12_ToggleButton);
        buttons.add(tag13_ToggleButton);
        buttons.add(tag14_ToggleButton);
        buttons.add(tag15_ToggleButton);
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
                //open ToursHistory Window
                App.scene = new Scene(App.loadFXML("ToursHistoryView"));
                Stage secondaryStage = new Stage();
                secondaryStage.setScene(App.scene);
                secondaryStage.show();

                //close this window
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
            
            LabelClearTextTransition(ErrorMessage_Label);
            return false;
        }
        if (missing_groups) {
            showMessage(ErrorMessage_Label, "You have no groups for your tour! Please insert the number of groups you have chosen or less.");
            LabelClearTextTransition(ErrorMessage_Label);
            return false;
        }

        //check for wrongly formatted data
        try{
            Float.valueOf(price_TextField.getText());
        }catch(Exception e){
            showMessage(ErrorMessage_Label, "Please insert a valid price for your tour.");
            LabelClearTextTransition(ErrorMessage_Label);
            return false;
        }

        try{
            if (Integer.valueOf(duration_TextField.getText())<=0){
                showMessage(ErrorMessage_Label, "Invalid duration for your tour");
                LabelClearTextTransition(ErrorMessage_Label);
                return false;
            }
        }catch(Exception e){
            showMessage(ErrorMessage_Label, "Invalid duration for your tour");
            LabelClearTextTransition(ErrorMessage_Label);
            return false;
        }
    

        show();
        loadTags();
        return true;
    }

    @FXML   //extra functionality to accomplish smaller more independent functions. I  
    public void addGroup(ActionEvent event){    //It is contained in the user's input procedure for tour info
        LocalDate current_date = dates_DatePicker.getValue();

        //check for missing or invalid date
        if (current_date == null || current_date.isBefore(LocalDate.now())){
            showMessage(ErrorMessage_Label, "Please select a valid date for your group.");
            LabelClearTextTransition(ErrorMessage_Label);
        }else {
            LocalTime endHour = startHour;  //initialize endhour
            //check for wrongly formed or missing data
            try{    //groups_number must be an integer
                groups_allowed = Integer.valueOf(groupsNo_TextField.getText());
            }catch (Exception e){
                showMessage(ErrorMessage_Label, "Invalid groups number.");
                LabelClearTextTransition(ErrorMessage_Label);
                return;
            }

            try{    //spots in group must be an integer
                spotsingroup = Integer.valueOf(pplingroup_TextField.getText());
            }catch (Exception e){
                showMessage(ErrorMessage_Label, "Invalid spots in group number.");
                LabelClearTextTransition(ErrorMessage_Label);
                return;
            }

            try {  //startHour must be in LocalTime format
                startHour = LocalTime.parse(starthour_TextField.getText());
            }
            catch(Exception e){
                showMessage(ErrorMessage_Label, "Invalid start hour. Hours format is hh:mm");
                LabelClearTextTransition(ErrorMessage_Label);
                return;
            }

            try{ //duration must be a positive integer
                minutes = Integer.valueOf(duration_TextField.getText());
                endHour = startHour.plusMinutes(minutes);
                if (!endHour.isAfter(startHour)){
                    showMessage(ErrorMessage_Label, "Invalid duration for your tour.");
                    LabelClearTextTransition(ErrorMessage_Label);
                    return;
                }
            }catch(Exception e){
                showMessage(ErrorMessage_Label, "Invalid duration for your tour.");
                LabelClearTextTransition(ErrorMessage_Label);
                return;
            }

            //empty list, add new group
            if(groupslist.isEmpty()){
                groupslist.add(current_date.toString()+" "+startHour+"-"+endHour);
                if (!groupsNo_TextField.isDisabled() || !pplingroup_TextField.isDisabled()){
                    duration_TextField.setDisable(true);
                    groupsNo_TextField.setDisable(true);
                    pplingroup_TextField.setDisable(true);
                }
            }
            else if (groupslist.contains(current_date+" "+startHour+"-"+endHour)){
                //group (date and hours) already esist
                showMessage(ErrorMessage_Label, "Group already exists");
                LabelClearTextTransition(ErrorMessage_Label);
            }else {
                //count groups on current_date
                Pattern date_regex = Pattern.compile(current_date.toString()+".*");
                int i;
                int groups_inserted = 0;
                for (i=0; i<groupslist.size(); i++){
                    Matcher matcher = date_regex.matcher(groupslist.get(i));
                    if (matcher.find()) groups_inserted++;
                }
 
                if (groups_inserted<groups_allowed){
                    //insert the group (date and time available) for the tour in the List
                    groupslist.add(current_date.toString()+" "+startHour+"-"+endHour);
                    
                }else{ //reached group number: show Message 
                    showMessage(ErrorMessage_Label, "You have reached the maximum number of groups per date you have chosen");
                    LabelClearTextTransition(ErrorMessage_Label);
                }
            }
        }     
    }

    @FXML
    void uploadFile(ActionEvent event){
        Stage stage = (Stage) upload_Button.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll();
				
	    fileChooser.setTitle("Open My File");
				
		fileChooser.setInitialDirectory(new File("C:/"));
				
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) { //??? what for ???
			 System.out.println("Open File");
			 System.out.println(selectedFile.getPath());
        }

        uploadedfiles_ListView.getItems().add(selectedFile.getPath());
        

    }

    @FXML
    void checkFileSize(ActionEvent event) throws IOException{
        List<String> fileslist = uploadedfiles_ListView.getItems();
        for (int f=0; f<fileslist.size(); f++){
            Path tempP = Paths.get(fileslist.get(f));
            long bytes = Files.size(tempP);

            if (bytes>20*Math.pow(2,20)){  //filesize must be < 20 MB
                showMessage(invalidFsize_Label, tempP+" : file size must be < 20 MB");
                LabelClearTextTransition(invalidFsize_Label);
                fileslist.remove(f);
                return;
            }
        }
        show();
    }
    
    @FXML
    void bankPayment(ActionEvent event){
        paymentField1_TextField.setPromptText("FULL NAME");
        paymentField2_TextField.setVisible(true);
        paymentField3_TextField.setVisible(true);
    }
   
    @FXML   //extra functionality: It is contained in the select payment method from user input 
    void paypalPayment(ActionEvent event){
        paymentField1_TextField.setPromptText("EMAIL");
        paymentField2_TextField.setVisible(false);
        paymentField3_TextField.setVisible(false);
    }

    @FXML
    void validatePaymentMethod(ActionEvent event) throws IOException{
        if (paymentField2_TextField.isVisible()){   //bank account payment
            String orgname = paymentField1_TextField.getText();
            String address = paymentField2_TextField.getText();
            String IBAN = paymentField3_TextField.getText();

            if (App.current_type.equals("TourGuide")){
                //check for missing fields
                if (orgname.isEmpty() || IBAN.isEmpty()){
                    showMessage(help_Label, "Fields mising. Please fill the following fields.");
                    return;
                }
                else{
                    //no missing fields
                    //check for correct organisation name == tourguide's username
                    if (!orgname.equals(App.current_user)){
                        showMessage(help_Label,"Verification failed. There is wrong data.");
                        return;
                    }

                    //check for correct IBAN
                    try {
                        Statement stm = DBcommunicator.getConnection().createStatement();
                        String getIBAN = "select iban from tourguide inner join User on tourguide.tgid = user.id where user.username =\""+App.current_user+"\";";
                        
                        ResultSet dbIBAN = stm.executeQuery(getIBAN);

                        while (dbIBAN.next()){
                            if (!dbIBAN.getString(1).equals(IBAN)){
                                showMessage(help_Label,"Verification failed. There is wrong data.");
                                dbIBAN.close();
                                return;
                            }
                        }
                        dbIBAN.close();
                    }catch (SQLException e){
                        e.printStackTrace();
                        return;
                    }  

                }
            }else{  //only tour guieds and hosts see this view
                //check for host's missing fields
                if (orgname.isEmpty() || address.isEmpty() || IBAN.isEmpty()){
                    showMessage(help_Label, "Fields mising. Please fill the following fields.");
                    return;
                }
                else{
                    //no missing fields
                    //check for correct organisation data
                    try {
                        Statement stm = DBcommunicator.getConnection().createStatement();
                        String getIBAN = "select hname, haddress, iban from host inner join uset on host.hid = user.id where user.username =\" "+App.current_user+"\";";
                        
                        ResultSet dborgdata = stm.executeQuery(getIBAN);

                        while (dborgdata.next()){
                            if (!dborgdata.getString(1).equals(orgname) || !dborgdata.getString(2).equals(address) || !dborgdata.getString(3).equals(IBAN)){
                                showMessage(help_Label,"Verification failed. There is wrong data.");
                                dborgdata.close();
                                return;
                            }
                        }
                        dborgdata.close();
                    }catch (SQLException e){
                        e.printStackTrace();
                        return;
                    }  

                }    
            }   
        } else{
            String email = paymentField1_TextField.getText(); 

            if (email.isEmpty()){   //missing data
                showMessage(help_Label, "Missing fields. Please fill the following fields.");
                return;
            }else{  //wrong data
                String query = "select email from user where username = \""+App.current_user+"\";";
                try{
                    Statement stm = DBcommunicator.getConnection().createStatement();
                    ResultSet dbemail = stm.executeQuery(query);

                    while (dbemail.next()){
                        if (!dbemail.getString(1).equals(email)){
                            showMessage(help_Label, "Verification failed. There is wrong data.");
                            dbemail.close();
                            return;
                        }
                    }
                    dbemail.close();
                }catch(SQLException e){
                    e.printStackTrace();
                    return;
                }

            }
        }

        //all correct!
        save_files();

        //Tour registration
        createTour();
        
        show();

    }

    @FXML   //extra functionality: applying to front end only
    void publicselection(ActionEvent event){
        save_ToggleButton.setSelected(false);
    }

    @FXML   //extra functionality: applying to front end only
    void saveselection(ActionEvent event){
        publish_ToggleButton.setSelected(false);
    }

    @FXML   
    void publicize(ActionEvent event) throws IOException{
        if (publish_ToggleButton.isSelected()){
            creation.setIsPublic(true);
        }
        //update public field of the row in the db as well
        try {
            Statement stm = DBcommunicator.getConnection().createStatement();
            String publicupdt = "update tour set public=1 where id=\""+creation.getId()+"\";";
            stm.execute(publicupdt);

        }catch(SQLException e){
            e.printStackTrace();
        }
        
        show();
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

    private void save_files() throws IOException{
        List<String> fileslist = uploadedfiles_ListView.getItems();

        s = "./src/main/saved_data/"+App.current_type+"/"+App.current_user+"/Tours/"+title_TextField.getText()+"/";
        for (int f=0; f<fileslist.size(); f++){
            Path tempP = Paths.get(fileslist.get(f));
            File source = new File(fileslist.get(f));
            File dest = new File(s+source.getName());
            
            dest.getParentFile().mkdirs();
            Files.copy(tempP, dest.toPath());             
        } 
          
    }

    private void createTour(){
        //initialize object fields
        String title = title_TextField.getText();
        List<String> tags = new ArrayList<String>();

        for (int i=0; i<buttons.size(); i++){
            if (buttons.get(i).isSelected()){
                tags.add(buttons.get(i).getText());
            }
        }
        String tourguidename = App.current_user;
        String description = description_TextArea.getText();
        String location = location_TextField.getText();
        float price = Float.valueOf(price_TextField.getText());

        //set object attributes
        creation.setTitle(title);
        creation.setTags(tags);
        creation.setVirtual(false);
        creation.setTourGuide(tourguidename);
        creation.setDescription(description);
        creation.setDuration(minutes);
        creation.setIsPublic(false);
        creation.setDatePublished(new Date());
        creation.setDatesAvailable(groupslist);
        creation.setGroupsPerDate(groups_allowed);
        creation.setSpotsPerGroup(spotsingroup);
        creation.setLocation(location);
        creation.setPrice(price);
        creation.setRating(0);
        creation.setTimesBought(0);
        creation.setFilePath(s);

       
        //store object in DB
        creation.store();

    }

    //extra functionality: applying to front end only
    private void LabelClearTextTransition(Label o){
        PauseTransition visibleTextPause = new PauseTransition(
            Duration.seconds(5)
            );
            visibleTextPause.setOnFinished(
                event -> o.setText("")
            );
            visibleTextPause.play();
    }
    
    private void showMessage(Label l, String s){
        l.setText(s);
    }

}
