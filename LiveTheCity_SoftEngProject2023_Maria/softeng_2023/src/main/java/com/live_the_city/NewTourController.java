package com.live_the_city;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class NewTourController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    //--------- New Tour View -----------//
    @FXML
    private AnchorPane NewTourView;
    @FXML
    private Button next_Button;
    @FXML
    private TextField title_TextField;
    @FXML
    private CheckBox virtual_CheckBox;
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
    private ChoiceBox<LocalTime> hours_ChoiceBox;
    @FXML
    private Button creategroup_Button;
    @FXML
    private ListView<String> groups_List;
    @FXML 
    private TextField price_TextField;
    //-----------TagsSelectView------------//
    @FXML
    private AnchorPane TagsSelectView;

    //-----------FilesUploadView------------//
    @FXML
    private AnchorPane FilesUploadView;

    //-----------PaymentMethodView------------//
    @FXML
    private AnchorPane PaymentMethodView;

    //-----------PublishTourView------------//
    @FXML
    private AnchorPane PublishTourView;

    //non-FXML Controller variables//
    //prev_view anchor pane to go back
    private AnchorPane current_view;


    //Navigation Functions//
    @FXML
    void initialize() {
        current_view =  NewTourView;

        NewTourView.setVisible(true);
        TagsSelectView.setVisible(false);
     //   FilesUploadView.setVisible(false);
     //   PaymentMethodView.setVisible(false);
     //   PublishTourView.setVisible(false);
    }

    @FXML
    void showTagsSelectView(ActionEvent event){
        current_view.setVisible(false);
        switch (current_view.getId()){
            case "NewTourView":
                TagsSelectView.setVisible(true);
                current_view = TagsSelectView;
                break;
            case "TagsSelectView":
                break;
        }
        
        current_view = TagsSelectView; 
        
    }
     
    

}
