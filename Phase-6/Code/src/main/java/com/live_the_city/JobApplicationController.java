package com.live_the_city;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class JobApplicationController {

//-------------------------FXML variables--------------------------

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField firstNameTxt;

    @FXML
    private TextArea messageTxt;

    @FXML
    private TextField phoneTxt;

    @FXML
    private Button sendApplicationBtn;

    @FXML
    private TextField surnameTxt;

    @FXML
    private Button uploadFileBtn;

    @FXML
    private Button okBtn;

//--------------------------Other variables------------------------------

    private User applicant = new User(3,"sample-username","sample-password","sample-email"); //sample-User
    private JOffer selectedOffer;
    private String fullname;
    private String firstName;
    private String surname;
    private String phone;
    private String email;
    private String message;
    private String filePath = null;
    private boolean validApplication;

    

//------------FXML methods--------------------------------
    @FXML
    void saveApplication(ActionEvent event) throws SQLException{
            System.out.println(selectedOffer);
            createApplication();
        
            String query = "INSERT INTO jobapplication(id, responding_to, applicant, fullname, phone, email, message, apply_date, status, file_path) VALUES(null,'" + this.selectedOffer.getId() + "','" + this.applicant.getId() + "','" + fullname + "','" + phone + "','" + email + "','" + message + "',now(), 'Sent','" + filePath + "')";
            System.out.println(query);
            Statement statement = DBcommunicator.getConnection().createStatement();
            statement.execute(query);

            System.out.println("Your Job Application is saved in the database");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sent");
            alert.setHeaderText(null);
            alert.setContentText("Your Application was successfully sent!");
            
            alert.showAndWait();
            

    }

    @FXML
    void initialize() {

    }

    @FXML
    void uploadFile(ActionEvent event) {
        Stage stage = (Stage) uploadFileBtn.getScene().getWindow();
		ExtensionFilter ex1 = new ExtensionFilter("All Files","*.*");
        FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(ex1);
				
	    fileChooser.setTitle("Open My File");
				
		fileChooser.setInitialDirectory(new File("C:/"));
				
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
			 System.out.println("Open File");
             if(validateFileSize(selectedFile))
             { 
                this.filePath = selectedFile.getPath();
             }
		}

    }

    @FXML
    void validateJobApp(ActionEvent event) {

        firstName = firstNameTxt.getText().trim();
        surname = surnameTxt.getText().trim();
        phone = phoneTxt.getText().trim();
        email = emailTxt.getText().trim();
        message = messageTxt.getText().trim();

        if(firstName.isEmpty() || surname.isEmpty() || phone.isEmpty() || email.isEmpty() ||message.isEmpty())
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please enter the required fields");
            alert.showAndWait();
            System.out.println("Please enter the required field");
            validApplication = false;

        }
        else{
            validApplication = true;
            uploadFileBtn.setDisable(false);
            sendApplicationBtn.setDisable(false);
        }

       

    }


//-------------------Other methods---------------------------------------------

    void createApplication(){

        if(validApplication)
        {

        fullname = firstName + " " + surname;
        new JobApplication(this.applicant, this.selectedOffer, fullname, phone, email, message, LocalDateTime.now(), filePath);
        }
        System.out.println("Creating Application...");
    }
    

    private boolean validateFileSize(File file){
        
        Long fileSize = file.length(); // file size in bytes
        if(fileSize < 200000)
        {
            return true;
        }
        else
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalide file size");
            alert.setContentText("The file is too large, please try again.");
            alert.showAndWait();
            System.out.println("Invalide file size");
            return false;
        }
    }

    public void setSelectedOffer(JOffer offer){
        this.selectedOffer = offer;
    }
}