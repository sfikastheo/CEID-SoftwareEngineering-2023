package com.live_the_city;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.live_the_city.Event.Planner_type;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class EventsController {

    @FXML
    private AnchorPane criteriaPane;

    @FXML
    private TableView  eventsTable;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button searchBtn;

    @FXML
    private Button selectEventBtn;

    private List<String> criteria;

    
    @FXML
    void selectCriteria(MouseEvent event) {
        criteriaPane.setVisible(false);
        mainPane.setVisible(true);

        showEventList();
        
    }

    @FXML
    void selectEvent(MouseEvent event) {
        List item = (List) eventsTable.getSelectionModel().getSelectedItem();
        String id  = (String)item.get(0);
        System.out.println();
        getEventDetails(id);
    }

    public void displayEvent(Event event) {
        EventsDetController.setEvent(event);

        try{
            Scene scene = new Scene(App.loadFXML("eventsdet"), 1280, 800);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        

            Stage currentStage =  (Stage) mainPane.getScene().getWindow();
            currentStage.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void getEventDetails(String id){
        try{
            Connection conn = DBcommunicator.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from event where id = ?");
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            List<String> tags = new ArrayList<String>();
            Planner_type planner=Planner_type.Host;

            Event event = new Event(rs.getInt(1), rs.getString(2), tags, planner, rs.getString(4), rs.getDate(5), rs.getString(6), rs.getFloat(8), rs.getInt(9));

            displayEvent(event);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void showEventList(){
        try{
            //Establish connection to db
            Connection connection = DBcommunicator.getConnection();
            PreparedStatement query = connection.prepareStatement("SELECT id, title, event_location FROM event");
            ResultSet rs = query.executeQuery();

            //Load table contents
            setTable(rs, eventsTable);

           // connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

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
    

}
