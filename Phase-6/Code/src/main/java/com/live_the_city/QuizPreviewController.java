package com.live_the_city;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

public class QuizPreviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton falseBtn1;

    @FXML
    private RadioButton falseBtn2;

    @FXML
    private RadioButton falseBtn3;

    @FXML
    private Text question1;

    @FXML
    private Text question2;

    @FXML
    private Text question3;

    @FXML
    private Text quizDescription;

    @FXML
    private Text quizTitle;

    @FXML
    private RadioButton trueBtn1;

    @FXML
    private RadioButton trueBtn2;

    @FXML
    private RadioButton trueBtn3;


    private ArrayList<Text> texts = new ArrayList<>();
    private ArrayList<RadioButton> trueBtns = new ArrayList<>();
    private ArrayList<RadioButton> falseBtns = new ArrayList<>();


    public void displayQuizTitle(String title) {
        quizTitle.setText(title);
    }

    public void displayQuizDescription(String description) {
        quizDescription.setText(description);
    }

    public void displayquestions(ArrayList<Question> questions) {

        int num = questions.size();
        System.out.println(num);
        
        for(int i = 0; i < num; i++)
        {
            trueBtns.get(i).setVisible(true);
            falseBtns.get(i).setVisible(true);
        
            texts.get(i).setText(questions.get(i).getQuestion());
            if(questions.get(i).getAnswer()=="true")
            {
                falseBtns.get(i).setDisable(true);
                trueBtns.get(i).setSelected(true);
            }
            else
            {
                trueBtns.get(i).setDisable(true);
                falseBtns.get(i).setSelected(true);
            }
        }
    }
    

    @FXML
    void initialize() {
      
        texts.add(question1);
        texts.add(question2);
        texts.add(question3);
        trueBtns.add(trueBtn1);
        trueBtns.add(trueBtn2);
        trueBtns.add(trueBtn3);
        falseBtns.add(falseBtn1);
        falseBtns.add(falseBtn2);
        falseBtns.add(falseBtn3);


    }
    


    
}
