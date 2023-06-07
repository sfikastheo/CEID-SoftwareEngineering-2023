package com.live_the_city;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ToursHistoryController {

    //-------ToursHistory View--------//
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button Back_Button;
    @FXML
    private Button newTour_Button;
    @FXML
    private Button quiztourConnBtn;
    @FXML
    private TableView<Tour> toursTableView;
    @FXML
    private TableColumn<Tour, Integer> TourId_Column;
    @FXML 
    private TableColumn<Tour, String> Title_Column;
    @FXML 
    private TableColumn<Tour, Boolean> Virtual_Column;
    @FXML 
    private TableColumn<Tour, String> Description_Column;
    @FXML 
    private TableColumn<Tour, Boolean> Public_Column;
    @FXML 
    private TableColumn<Tour, String> DatePublished_Column;
    @FXML 
    private TableColumn<Tour, String> Rating_Column;

    //-----Other variables----------
    Quiz quiz;
    Tour selectedTour;
    private ObservableList<Tour> toursList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        //fill the table
        try{
            Statement stm = DBcommunicator.getConnection().createStatement();
            String query = "select tour.id, title, virtual, descr, public, datepublished, rating from tour inner join user on tour.offered_by=user.id where username=\""+App.current_user+"\";";
            ResultSet data = stm.executeQuery(query);

            while (data.next()){
                /*
                Timestamp ts = data.getTimestamp(5);
                //Date dt = new Date(ts.getTime());
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
                String finaldate = formatter.format(ts);
                */
                toursList.add(new Tour(data.getInt(1), data.getString(2), data.getBoolean(3), data.getString(4), data.getBoolean(5), data.getFloat(7)));                
            }
            data.close();

            TourId_Column.setCellValueFactory(new PropertyValueFactory<>("id"));
            Title_Column.setCellValueFactory(new PropertyValueFactory<>("title"));
            Virtual_Column.setCellValueFactory(new PropertyValueFactory<>("virtual"));
            Description_Column.setCellValueFactory(new PropertyValueFactory<>("description"));
           // Public_Column.setCellValueFactory(new PropertyValueFactory<>("ispublic"));  
           // DatePublished_Column.setCellValueFactory(new PropertyValueFactory<>("date_published"));
            Rating_Column.setCellValueFactory(new PropertyValueFactory<>("rating"));    
            toursTableView.setItems(toursList);
        }catch (SQLException e){
           e.printStackTrace();
        }
    }

    
    @FXML 
    void getSelectedTour(MouseEvent event) {
        this.selectedTour = toursTableView.getSelectionModel().getSelectedItem();
        quiztourConnBtn.setDisable(false);

    } // when a tour is selected from the table



    @FXML
    void createTour() throws IOException{
        //show NewTour Window
        App.scene = new Scene(App.loadFXML("NewTour_all"));
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(App.scene);
        secondaryStage.show();

        //close current window
        Stage currentstage = (Stage) newTour_Button.getScene().getWindow();
        currentstage.close();
    }

    @FXML
    void back2home() throws IOException{

        App.scene = new Scene(App.loadFXML("HomePage"));
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(App.scene);
        secondaryStage.show();

        Stage currentstage = (Stage) newTour_Button.getScene().getWindow();
        currentstage.close();
         
    }



    @FXML
    void quizTourConnection(ActionEvent event) {
        NewQuizController newQuizCon = new NewQuizController();
        newQuizCon.setSelectedTour(selectedTour); //sends the selected tour to NewQuizController
        Stage stage = (Stage) quiztourConnBtn.getScene().getWindow();
        stage.close(); //closes current view
    
    }


    //-------------------------Other functions-------------------------------
    
    void hide_back_newTour_Btns(){
        Back_Button.setVisible(false);
        newTour_Button.setVisible(false);
        quiztourConnBtn.setVisible(true);
    } //hides buttons when TourHistoryView is used for quiz-tour connection


}  

