package com.live_the_city;

import java.io.File;
import java.net.URL;
import java.sql.Statement;
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

    private User applicant;
   // private JobOffer responding_to;
    private String fullname;
    private String firstName;
    private String surname;
    private String phone;
    private String email;
    private String filePath = null;
    private JobApplication jobApplication;


    @FXML
    void saveApplication(ActionEvent event){
            //SAVING TO DATABASE
            /* 
            String query = "INSERT INTO JobAppliaction(id, responding_to, applicant, fullname, phone, email, message, apply_date, status, file_path) VALUES(null,'" + this.responding_to.getId() + "','" + this.applicant.getId() + "','" + fullname + "','" + phone + "','" + email + "','" + message + "',now(), 'Sent','" + filePath + "')";
            System.out.println(query);
            Statement statement = DatabaseConnection.getConnection().createStatement();
            statement.execute(query);

            System.out.println("Your Job Appication is saved in the database");
            */

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

    private boolean validateJobApplicationInfo()
    { 
        firstName = firstNameTxt.getText().trim();
        surname = surnameTxt.getText().trim();
        phone = phoneTxt.getText().trim();
        email = emailTxt.getText().trim();

        if(firstName.isEmpty() || surname.isEmpty() || phone.isEmpty() || email.isEmpty())
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please enter the required fields");
            alert.showAndWait();
            System.out.println("Please enter the required field");
            return false;

        }
        else{
            System.err.println("Creating Application...");
            return true;
        }

    }

    /*
    public void setResponding_to(JobOffer offer){
        this.responding_to = offer;

    }
    */

    void createApplication(){

        if(validateJobApplicationInfo())
        {

        fullname = firstName + " " + surname;
        //this.jobApplication = new JobApplication(applicant, responding_to, fullname, phone, email, filePath);
        }
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
}