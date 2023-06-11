
package com.live_the_city;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;


public class NewQuizController implements Initializable {
    
//-----------------------------FXML variables-----------------------------------
    @FXML
    private Button addImgBtn;

    @FXML
    private RadioButton falseRadio;

    @FXML
    private Button newQuestionBtn;

    @FXML
    private Button previewQuizBtn;

    @FXML
    private TextField quizQuestion;

    @FXML
    private TextArea quizDescription;

    @FXML
    private TextField quizTitle;

    @FXML
    private RadioButton trueRadio;

    @FXML
    private Button setQuizInfoBtn;

    @FXML
    public Button submitQuizBtn;


//----------------------------Other variables------------------------------------
    private ToggleGroup radioGroup;

    private Quiz quiz;

    private String title;

    private String description;

    private ArrayList<Question> questions = new ArrayList<>();

    public Tour selectedTour;

    private String filePath = null;

    NewQuizController nqc;



//---------------------------FXML methods-------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radioButtonSettup(); 
    } 

    @FXML
   private void createQuiz(ActionEvent event) {

        String title = quizTitle.getText().trim();
        String description = quizDescription.getText().trim();
        Boolean valid = validateQuizInfo(title);

        if(valid)
        {
          this.title = title;
          this.description = description;
          this.quiz = new Quiz(title, description, LocalDateTime.now());
         
        }

    }

    @FXML
    void uploadFile(ActionEvent event) {

        Stage stage = (Stage) addImgBtn.getScene().getWindow();
		ExtensionFilter ex1 = new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(ex1);
				
	    fileChooser.setTitle("Open My File");
				
		fileChooser.setInitialDirectory(new File("C:/"));
				
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
			 System.out.println("Open File");
			 System.out.println(selectedFile.getPath());
             if(validateFileSize(selectedFile))
             { 
                this.filePath = selectedFile.getPath();
             }
		}

    }


    @FXML
    void createQuestion(ActionEvent event) {
        String answer;
        String questiontxt = this.quizQuestion.getText().trim();
        Toggle selectedBtn = radioGroup.getSelectedToggle();
        Boolean valid = validateQuizQuestions(questiontxt, selectedBtn);
        Question question = new Question();

        if(valid)
        {

            if(selectedBtn == trueRadio)
            {
                answer = "true";
            }
            else
            {
                answer = "false";
            }
            question.setQuestion(questiontxt);
            question.setAnswer(answer);
            question.setFile_path(filePath);
            questions.add(question);

            quizQuestion.clear();
            this.filePath = null;
            selectedBtn.setSelected(false);
            previewQuizBtn.setDisable(false);


        }

    }


    @FXML
    void previewQuiz(ActionEvent event) throws IOException {
       
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ToursHistoryView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

        ToursHistoryController toursHistCont  = fxmlLoader.getController();

        toursHistCont.quizPreview();
        toursHistCont.displayQuizTitle(this.title);
        toursHistCont.displayQuizDescription(this.description);
        toursHistCont.displayquestions(questions);
        toursHistCont.setController(this.nqc);
       // pass_nqController();



    }




    @FXML
    void submitQuiz(ActionEvent event) {
        
        try{
            String query = "INSERT INTO quiz(qid, title, on_tour, descr, date_uploaded) VALUES(null,'"+this.quiz.getTitle()+"','"+ selectedTour.getId()+"','"+this.quiz.getDescription()+"', now())";
            //String query = "INSERT INTO Quiz(qid, title, descr, date_uploaded) VALUES(null,'"+this.quiz.getTitle()+"','"+this.quiz.getDescription()+"', now())";
            Statement statement = DBcommunicator.getConnection().createStatement();
            statement.execute(query);

            System.out.println("Your Quiz is saved in the database");

        
            query = "SELECT qid FROM quiz WHERE title = '"+this.quiz.getTitle()+"'";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
            int quizId = rs.getInt("qid");
            
            System.out.println(quizId);
            for(int i=0; i<questions.size(); i++) {
            query = "INSERT INTO quizQuestion(id, qtext, answer, on_quiz, qpath) VALUES(null,'"+questions.get(i).getQuestion()+"','"+questions.get(i).getAnswer()+"','"+quizId+"','"+ questions.get(i).getFile_path()+"')";
            statement.execute(query);
            System.out.println("The Quiz question "+ i +" is saved in the database");
             }
            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Submited");
            alert.setHeaderText(null);
            alert.setContentText("Your Quiz was successfully submited!");
            
            alert.showAndWait();
           }
        catch(Exception e) {
            
            e.printStackTrace();
    
           }


    }

    
//----------------------------Other methods------------------------------------
    private boolean validateQuizInfo( String title)
    { 
        
        if(title.isEmpty())
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Quiz title missing");
            alert.setContentText("Please enter the required field");
            alert.showAndWait();
            System.out.println("Please enter the required field");
            return false;

        }
        else{
            newQuestionBtn.setDisable(false);
            addImgBtn.setDisable(false);
            System.err.println("Setting quiz info...");
            return true;
        }

    }

    private boolean validateQuizQuestions(String question, Toggle answer)
    {
     

        if(question.isEmpty() || answer==null)
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Required information is missing");
            alert.setContentText("Please enter the required fileds");
            alert.showAndWait();
            System.out.println("Please enter the required fields");
            return false;

        }
        else
        {
    
            System.out.println("Setting the question...");
            return true;
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
    
    private void radioButtonSettup(){
        radioGroup = new ToggleGroup();
        trueRadio.setToggleGroup(radioGroup);
        falseRadio.setToggleGroup(radioGroup);
    }

    public Quiz getQuiz(){
        return this.quiz;
    }

    public void setSelectedTour(Tour tour){
         submitQuizBtn.setDisable(false);
         this.selectedTour = tour;
    }

    public void set_nqController(NewQuizController nqc){
            this.nqc = nqc;
    }

    
  
    

    

}


