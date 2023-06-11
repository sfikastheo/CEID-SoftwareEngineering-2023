package com.live_the_city;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class JOffersListController {

//------FXML variables --------------------------------


  //-----jobOfferInfoView --------------------------------
    @FXML
    private Button applyBtn;

    @FXML
    private Text offerDescription;

    @FXML
    private Text offerHost;

    @FXML
    private Text offerTitle;

    @FXML
    private AnchorPane jobOfferInfoView;


  //------jobOfferListView --------------------------------
    @FXML
    private TableView<JOffer> joffersTable;

    @FXML
    private TableColumn<JOffer, String> employer;

    @FXML
    private TableColumn<JOffer, String> title;

    @FXML
    private Button seeOfferInfoBtn;

    @FXML
    private Button showOffersBtn;

    @FXML
    private AnchorPane jobOfferListView;


//------other variables --------------------------------	

    public JOffer selectedOffer;
    private ObservableList<JOffer> JOffersList = FXCollections.observableArrayList();

//----------------FXML methods----------------
    @FXML
    void initialize() throws SQLException {
        updateTable();
        
    }



    @FXML
    void apply(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("JobApplicationScene.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stage2 = (Stage) applyBtn.getScene().getWindow();
        stage2.close();
        JobApplicationController jobAppCont = fxmlLoader.getController();
        jobAppCont.setSelectedOffer(this.selectedOffer);



    }

     
    @FXML 
    void selectedOffer(MouseEvent event) {
        selectedOffer = joffersTable.getSelectionModel().getSelectedItem();
        seeOfferInfoBtn.setDisable(false);
        System.out.println(selectedOffer.getDescription());

    } 

    @FXML
    void showOfferInfo(ActionEvent event) throws IOException {

        offerTitle.setText(selectedOffer.getTitle());
        offerDescription.setText(selectedOffer.getDescription());
        offerHost.setText(selectedOffer.getHost());
        jobOfferListView.setVisible(false);
        jobOfferInfoView.setVisible(true);
     
    

    }

//-------------- Other methods ----------------------------
    public void updateTable() throws SQLException{

        Statement statement;
        statement = DBcommunicator.getConnection().createStatement();
       
        String query = "SELECT host.hname, joboffer.id, joboffer.title, joboffer.descr FROM joboffer INNER JOIN host ON joboffer.employer = host.hid";
        statement.executeQuery(query);
        ResultSet rs = statement.getResultSet();

        System.out.println(rs.getFetchSize());
        while(rs.next())
        {
            JOffer joffer = new JOffer(rs.getInt("id"), rs.getString("hname"), rs.getString("title"), rs.getString("descr"));
            JOffersList.add(joffer);
            System.out.println("offer added");
        }
        
        employer.setCellValueFactory(new PropertyValueFactory<JOffer,String>("employer"));
        title.setCellValueFactory(new PropertyValueFactory<JOffer,String>("title"));
        joffersTable.setItems(JOffersList);


    }
    
}
