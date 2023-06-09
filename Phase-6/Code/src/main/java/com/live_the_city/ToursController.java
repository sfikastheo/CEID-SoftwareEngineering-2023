package com.live_the_city;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ToursController {


//-----------------FXML Attributes----------------
    @FXML
    private Button selectTourBtn;

    @FXML
    private TableView toursTable;

    @FXML
    private AnchorPane LinkPane;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button linkBtn;

    @FXML
    private TextField linkField;


  
//---------------------FXML Methods--------------------------------
    @FXML
    public void initialize() {
    
        try{
            //Establish connection to db
            Connection connection = DBcommunicator.getConnection();
            PreparedStatement query = connection.prepareStatement("SELECT id, title, startlocation FROM tour");
            ResultSet rs = query.executeQuery();

            //Load table contents
            setTable(rs, toursTable);

           // connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
  
    
    @FXML
    void getInvLink(MouseEvent event){
        List item = (List) toursTable.getSelectionModel().getSelectedItem();

        if(!item.isEmpty()){
            LinkPane.setVisible(true);
            mainPane.setVisible(false);
        }

       
    }

    @FXML
    void checkLink(MouseEvent event){
        String link = linkField.getText();
        System.out.println(link);

        selectTour();
    }


    //---------------------------Non FXM methods------------------------------------------

    //Loads data from the database to the table
    private static void setTable(ResultSet rs, TableView table){

        //List for the Data to be loaded
        ObservableList<ObservableList> data = FXCollections.observableArrayList();

            try{

                //Get the column names from resultset metadata
                for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                
                    final int j = i;                
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                    col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                        public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                            return new SimpleStringProperty(param.getValue().get(j).toString());                        
                        }                    
                    });
    
                    table.getColumns().addAll(col);
                }
                
                //Get the actual data and add them to the data list
                while(rs.next()){
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                        row.add(rs.getString(i));
                    }
                    data.add(row);
    
                }

                //Load table with the data from the list created
                table.setItems(data);          
            }
            catch (Exception e){
                e.printStackTrace();
            }
           
    }

    private void selectTour() {


        List item = (List) toursTable.getSelectionModel().getSelectedItem();
        String id = (String) item.get(0);
      
        showTourDet(id);
    }

    private void showTourDet(String id) {
        try{
            
            ToursDetController.setTourId(id);

            Scene scene = new Scene(App.loadFXML("toursdet"), 1280, 800);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

           

            Stage currentStage =  (Stage) toursTable.getScene().getWindow();
            currentStage.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
