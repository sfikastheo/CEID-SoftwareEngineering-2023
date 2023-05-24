package com.live_the_city;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
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
    private Button toTagsSelect_Button;
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
    
    //non-FXML Controller variables//
    //prev_view anchor pane to go back
    private AnchorPane current_view;


    //Navigation Functions//
    @FXML
    void initialize() {
        current_view =  NewTourView;

        NewTourView.setVisible(true);
        TagsSelectView.setVisible(false);
        FilesUploadView.setVisible(false);
        PaymentMethodView.setVisible(false);
        PublishTourView.setVisible(false);
    }

    @FXML
    void show(ActionEvent event){
        current_view.setVisible(false);
        switch (current_view.getId()){
            case "NewTourView":
                NewTourView.setVisible(false);
                TagsSelectView.setVisible(true);
                current_view = TagsSelectView;
                break;
            case "TagsSelectView":
                TagsSelectView.setVisible(false);
                FilesUploadView.setVisible(true);
                current_view = FilesUploadView;
                break;
            case "FilesUploadView":
                FilesUploadView.setVisible(false);
                PaymentMethodView.setVisible(true);
                current_view = PaymentMethodView;
                break;
            case "PaymentMethodView":
                PaymentMethodView.setVisible(false);
                PublishTourView.setVisible(true);
                current_view = PublishTourView;
                break;
            /*  case "PublishTourView":
                PublishTourView.setVisible(false);
                break; */
        }
    }
    
    
     
    

}
