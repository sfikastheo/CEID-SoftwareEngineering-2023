package com.live_the_city;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ChoiceBox;
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

public class NewJOfferController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button toTagsSelect_Button;

    //-----------Job Info View-----------//
    @FXML
    private AnchorPane JobInfoView;
    @FXML
    private TextField title_TextField;  
    @FXML
    private TextArea description_TextArea;
    @FXML
    private TextField employer_TextField;
    @FXML
    private TextField salary_TextField;
    @FXML
    private ChoiceBox<String> type_ChoiceBox;
    @FXML
    private DatePicker expirationdate_DatePicker;
    @FXML
    private TextField numemployees_TextField;
    @FXML 
    private Label ErrorMessageLabel;

    //-------------Tags Selct View-----------//
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
    
    //---------Files Upload View------------//
    @FXML
    private AnchorPane FilesUploadView;
    @FXML
    private Button toPublish_Button;
    @FXML
    private Button upload_Button;
    @FXML
    private ListView<String> uploadedfiles_ListView;
    @FXML
    private Label invalidFsize_Label;

    //-----------Publish JOffer View------------//
    @FXML
    private AnchorPane PublishJOfferView;
    @FXML
    private Button complete_Button;
    @FXML
    private ToggleButton publish_ToggleButton;
    @FXML
    private ToggleButton save_ToggleButton;

    //-----------NON-FXML VARIABLES---------//
    AnchorPane currentView;
    int emplnum;
    float salary;
    JOffer creation = new JOffer();
    String s;
    List<ToggleButton> buttons = new ArrayList<ToggleButton>();
    
    @FXML
    void initialize() {
        //views setup
        if (type_ChoiceBox.getItems().size() == 0)
        {
            type_ChoiceBox.getItems().add(0,"Full-Time");
            type_ChoiceBox.getItems().add(1, "Part-Time");
        }
        JobInfoView.setVisible(true);
        TagsSelectView.setVisible(false);
        FilesUploadView.setVisible(false);
        PublishJOfferView.setVisible(false);
        
        currentView = JobInfoView;

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
    void show(ActionEvent event) throws IOException{
        currentView.setVisible(false);
        switch (currentView.getId()){
            case "JobInfoView":
                TagsSelectView.setVisible(true);
                currentView = TagsSelectView;
                break;
            case "TagsSelectView":
                FilesUploadView.setVisible(true);
                currentView = FilesUploadView;
                break;
            case "FilesUploadView":
                PublishJOfferView.setVisible(true);
                currentView = PublishJOfferView;
                break;
            case "PublishJOfferView":
                //open ToursHistory Window
                App.scene = new Scene(App.loadFXML("MyJobOffers"));
                Stage secondaryStage = new Stage();
                secondaryStage.setScene(App.scene);
                secondaryStage.show();

                //close this window
                Stage currentstage = (Stage) complete_Button.getScene().getWindow();
                currentstage.close();
                break;
        }

    }

    @FXML
    void validateJOfferInfo(ActionEvent event) throws IOException{
        boolean missing_info = title_TextField.getText().isEmpty() || description_TextArea.getText().isEmpty() || employer_TextField.getText().isEmpty() || salary_TextField.getText().isEmpty() || numemployees_TextField.getText().isEmpty() || expirationdate_DatePicker.getValue() == null;

        if (missing_info || type_ChoiceBox.getSelectionModel().getSelectedItem() == null){
            showMessage(ErrorMessageLabel, "There are fields missing. All the fields are necessary.");
            return;
        }

        //wrongly formed data
        try{
            emplnum = Integer.valueOf(numemployees_TextField.getText());
            salary = Float.valueOf(salary_TextField.getText());
            if (emplnum<0){
                showMessage(ErrorMessageLabel, "Incorrectly formed numerical data.");
                LabelClearTextTransition(ErrorMessageLabel);
                return;
            }
            
        }catch(Exception e){
            showMessage(ErrorMessageLabel, "Incorrectly formed numerical data.");
            LabelClearTextTransition(ErrorMessageLabel);
            return;
        }

        //wrong Employer = hname from DB
        try{
            Statement stm = DBcommunicator.getConnection().createStatement();
            String query = "select hname from host inner join user on user.id=host.hid where username=\""+App.current_user+"\";";
            ResultSet hname = stm.executeQuery(query);
            
            while (hname.next()){
                if (!employer_TextField.getText().equals(hname.getString(1))){
                    showMessage(ErrorMessageLabel, "Incorrect Employer's name");
                    return;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        

        show(event);
        loadTags();
    }

    private void loadTags(){
        ObservableList<String> tagnames = FXCollections.observableArrayList();
        String getTags = "Select tagname from tags;"; 
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
    
    @FXML   //extra functionality: applying to GUI only
    void publicselection(ActionEvent event){
        save_ToggleButton.setSelected(false);
    }

    @FXML   //extra functionality: applying to GUI only
    void saveselection(ActionEvent event){
        publish_ToggleButton.setSelected(false);
    }

    @FXML
    void publicize(ActionEvent event) throws IOException{
        if (publish_ToggleButton.isSelected()){
            creation.setIspublic(true);
            try{
                //insert final Tour object in db
                Statement stm = DBcommunicator.getConnection().createStatement();
                String query = "update joboffer set public=1 where id="+creation.getId()+";";
                stm.execute(query);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        
        show(event);
    }

    void showMessage(Label l, String s){
        l.setText(s);
        LabelClearTextTransition(ErrorMessageLabel);
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

        save_files();
        
        //new JOffer Object creation
        //fields inialization
        createJOffer();
    
        show(event);
    }

    private void createJOffer(){
        //initialize object fields
        String employer = employer_TextField.getText();
        String date_opened = LocalDateTime.now().toString();
        String expires = expirationdate_DatePicker.getValue().toString();
        String title = title_TextField.getText();
        String describe = description_TextArea.getText();
        emplnum= Integer.valueOf(numemployees_TextField.getText());
        salary = Float.valueOf(salary_TextField.getText());

        creation.setTitle(title);
        creation.setDescription(describe);
        creation.setDateOpened(date_opened);
        creation.setExpires(expires);
        creation.setNumEmployees(emplnum);
        creation.setSalary(salary);
        creation.setIspublic(false);
        creation.setHost(employer);
        creation.setType(type_ChoiceBox.getSelectionModel().getSelectedItem());
        creation.setFilesPath(s);

        List<String> tags = new ArrayList<String>();

        for (int i=0; i<buttons.size(); i++){
            if (buttons.get(i).isSelected()){
                tags.add(buttons.get(i).getText());
            }
        }
        creation.setTags(tags);

        creation.store();
       
    }
    
    private void save_files() throws IOException{
        List<String> fileslist = uploadedfiles_ListView.getItems();

        s = "./src/main/saved_data/"+App.current_type+"/"+App.current_user+"/JOffers/"+title_TextField.getText()+"/";
        
        for (int f=0; f<fileslist.size(); f++){
            Path tempP = Paths.get(fileslist.get(f));
            File source = new File(fileslist.get(f));
            File dest = new File(s+source.getName());
            
            dest.getParentFile().mkdirs();
            Files.copy(tempP, dest.toPath());             
        } 
          
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

}
