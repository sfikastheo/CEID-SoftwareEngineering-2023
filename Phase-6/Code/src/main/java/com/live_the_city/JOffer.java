package com.live_the_city;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

public class JOffer {
    private int id;
    private String employer;
    private String title;
    private String description;
    private List<String> tags;
    private int num_employeees;
    private float salary;
    private String date_opened;
    private String expires;
    private String files_path;
    private boolean ispublic = false;
    private String type;

    public JOffer(int id, String host, String title, String desc, List<String> tags, int emno, float sal, String dop, String exp, String path, boolean pbl ){
        this.id = id;
        this.employer = host;
        this.title = title;
        this.description = desc;
        this.tags = tags;
        this.num_employeees = emno;
        this.salary = sal;
        this.date_opened = dop;
        this.expires = exp;
        this.files_path = path;
        this.ispublic = pbl;
    }

    public JOffer(String title, String desc, String dop, String exp, int emno, float sal, boolean pbl){
        this.title = title;
        this.description = desc;
        this.date_opened = dop;
        this.num_employeees = emno;
        this.salary = sal;
        this.ispublic = pbl;
    }

    public JOffer(int id, String employer, String title, String description)
    {
        this.id = id;
        this.employer = employer;
        this.title = title;
        this.description = description;
    } //second constructor for functionality


    public JOffer(){}

    public void store(){
        try{
            //store JOffer object in DB
            PreparedStatement pstm;
            String insertion = "insert into joboffer values(NULL,?,?,?,?,?,?,?,?,0,?);";

            pstm = DBcommunicator.getConnection().prepareStatement(insertion);
            pstm.setString(2, LocalDateTime.now().toString());
            pstm.setString(3, this.getExpires());
            pstm.setString(4, this.getTitle());
            pstm.setString(5, this.getDescription());
            pstm.setInt(6, this.getNumEmployees());
            pstm.setFloat(7, this.getSalary());
            pstm.setString(8, this.getFilesPath());
            pstm.setString(9, this.getType());
            
            Statement stm = DBcommunicator.getConnection().createStatement();
            String idquery = "select  id from user where username=\""+App.current_user+"\";";
            ResultSet employerId =  stm.executeQuery(idquery);

            while (employerId.next()){
                pstm.setInt(1, employerId.getInt(1));
            }
            employerId.close();

            pstm.executeUpdate();

            //return db id to the object
            idquery = "select id from joboffer order by id desc limit 1;";
            ResultSet myId = stm.executeQuery(idquery);

            while (myId.next()){
                this.setId(myId.getInt(1));
            }
            myId.close();

            //store tags for Joffer
            for (int i=0; i<this.tags.size(); i++){
                String query = "insert into JOtags values(NULL,"+this.getId()+",?);";
                pstm = DBcommunicator.getConnection().prepareStatement(query);

                idquery = "select id from tags where tagname=\""+tags.get(i)+"\";";
                ResultSet tagId = stm.executeQuery(idquery);

                while (tagId.next()){
                    pstm.setInt(1, tagId.getInt(1));
                }
                tagId.close();
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int getId(){
        return this.id;
    }
    public void setId(int newid){
        this.id = newid;
    }

    public String getHost(){
        return this.employer;
    }
    public void setHost(String newhost){
        this.employer = newhost;
    }

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String newtitle){
        this.title = newtitle;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String newdesc){
        this.description = newdesc;
    }

    public List<String> getTags(){
        return this.tags;
    }
    public void setTags(List<String> newtags){
        this.tags = newtags;
    }

    public int getNumEmployees(){
        return this.num_employeees;
    }
    public void setNumEmployees(int newnum){
        this.num_employeees = newnum;
    }

    public float getSalary(){
        return this.salary;
    }
    public void setSalary(float newsal){
        this.salary = newsal;
    }

    public String getDateOpened(){
        return this.date_opened;
    } 
    public void setDateOpened(String newdo){
        this.date_opened = newdo;
    }

    public String getExpires(){
        return this.expires;
    } 
    public void setExpires(String newexp){
        this.expires = newexp;
    }

    public String getFilesPath(){
        return this.files_path;
    }
    public void setFilesPath(String newfp){
        this.files_path = newfp;
    }

    public boolean getIspublic(){
        return this.ispublic;
    }
    public void setIspublic(boolean newpbl){
        this.ispublic = newpbl;
    }

    public String getType(){
        return this.type;
    }
    public void setType(String newtype){
        this.type = newtype;
    }
 
}
