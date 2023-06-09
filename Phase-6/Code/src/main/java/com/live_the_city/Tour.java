package com.live_the_city;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

public class Tour {
    //ATTRIBUTES
    private int id;
    private String title;
    private List<String> tags;
    private boolean virtual;
    private String offered_by;
    private String description;
    private int duration;
    private boolean ispublic; 
    private Date date_published;
    private List<String> dates_available;
    private List<Date> dates_available2;
    private int groups_per_date;
    private int spots_per_group;
    private String location;
    private float price;
    private float rating;
    private int times_bought;
    private String filespath = "./src/main/saved_data/"+App.current_type+"/"+App.current_user+"/Tours/";

    //METHODS
    public Tour(int id, String title, List<String> tags, boolean virt, String tg, String desc, int mins, boolean pbl, Date dtpbl, List<String> dtavail, int gpd, int spg, String loc, float price, float rate, int tb)
    {
        this.id = id;
        this.title = title;
        this.tags = tags;
        this.virtual = virt;
        this.offered_by = tg;
        this.description = desc;
        this.duration = mins;
        this.ispublic = pbl;
        this.date_published = dtpbl;
        this.dates_available = dtavail;
        this.groups_per_date = gpd;
        this.spots_per_group = spg;
        this.location = loc;
        this.price = price;
        this.rating = rate;
        this.times_bought = tb;
    }

    public Tour(int id, String title, boolean virt, String tg, String desc, int mins, boolean pbl, Date dtpbl, List<Date> dtavail, int gpd, int spg, String loc, float price, float rate, int tb)
    {
        this.id = id;
        this.title = title;
        this.virtual = virt;
        this.offered_by = tg;
        this.description = desc;
        this.duration = mins;
        this.ispublic = pbl;
        this.date_published = dtpbl;
        this.dates_available2 = dtavail;
        this.groups_per_date = gpd;
        this.spots_per_group = spg;
        this.location = loc;
        this.price = price;
        this.rating = rate;
        this.times_bought = tb;
    }

    public Tour(){}
    
    public Tour(int id, String title, boolean virtual, String desc, boolean pbl, float rate){
        this.id = id;
        this.title = title;
        this.virtual = virtual;
        this.description = desc;
        this.ispublic = pbl;
        this.rating = rate;
    }

    public void updateStats(int n){
        times_bought = times_bought+n;
    }

    public void store(){
        try {
            //store object in DB
           PreparedStatement pstm;
           String query = "insert into tour values(NULL,?,0,?,?,?,?,?,0,?,?,0,0,?,\""+this.getFilesPath()+"\");";
           pstm = DBcommunicator.getConnection().prepareStatement(query);

           pstm.setString(1, this.getTitle());
           pstm.setString(2, this.getDescription());
           pstm.setInt(3, this.getDuration());
           pstm.setString(4,this.getLocation());
           pstm.setInt(5,this.getGroupsPerDate());
           pstm.setInt(6, this.getSpotsPergroup());
           pstm.setString(7, LocalDateTime.now().toString());
           pstm.setFloat(8, price);

           Statement stm= DBcommunicator.getConnection().createStatement();
           String currentId = "select id from user where username=\""+App.current_user+"\";";
           ResultSet idset = stm.executeQuery(currentId);

           while (idset.next()){
               pstm.setInt(9, idset.getInt(1));
           }
           idset.close();

           pstm.executeUpdate();

           //return db id to the object
           query = "select id from tour where title=\""+title+"\";";
           ResultSet idset2 = stm.executeQuery(query);
           while(idset2.next()){
               this.setId(idset2.getInt(1));
           } 
           idset2.close();

           //store dates available
           query ="insert into dates4tour values(NULL,"+this.getId()+",?);";
           pstm = DBcommunicator.getConnection().prepareStatement(query);
           for (int i=0; i<this.getDatesAvailable().size(); i++){
           
               pstm.setString(1, this.getDatesAvailable().get(i));
               pstm.executeUpdate();
           }

           //store TEtags
           query ="insert into tetags values(NULL,?,"+this.getId()+",NULL);";
           pstm = DBcommunicator.getConnection().prepareStatement(query);
           stm = DBcommunicator.getConnection().createStatement();
           for (int i=0; i<tags.size(); i++){
               currentId = "select id from tags where tagname=\""+tags.get(i)+"\";";
               ResultSet tagid = stm.executeQuery(currentId);

               while (tagid.next()){
                   pstm.setInt(1, tagid.getInt(1));
                   pstm.executeUpdate();
               }
               tagid.close();
           }

           
       }catch (SQLException e){
           e.printStackTrace();
       }

    }
    
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public List<String> getTags(){
        return this.tags;
    }
    public void setTags(List<String> new_tags){
        this.tags = new_tags;
    }
    public void addTag(String tag){
        this.tags.add(this.tags.size(), tag);
    }
    public void removeTag(String tag){
        if (this.tags.contains(tag)){
            int index = this.tags.indexOf(tag);
            this.tags.remove(index);
        }
            
    }
    
    public boolean getVirtual(){
        return this.virtual;
    }
    public void setVirtual(boolean virt){
        this.virtual = virt;
    }
    
    public String getTourGuide(){
        return this.offered_by;
    }
    public void setTourGuide(String tg){
        this.offered_by = tg;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String desc){
        this.description = desc;
    }

    public int getDuration(){
        return this.duration;
    }
    public void setDuration(int mins){
        this.duration = mins;
    }

    public boolean getIsPublic(){
        return this.ispublic;
    }
    public void setIsPublic(boolean pbl){
        this.ispublic = pbl;
    }

    public Date getDatePublished(){
        return this.date_published;
    }
    public void setDatePublished(Date dtpbl){
        this.date_published = dtpbl;
    }

    public List<String> getDatesAvailable(){
        return this.dates_available;
    }
    public void setDatesAvailable(List<String> dtavail){
        this.dates_available = dtavail;
    }
    public void addDateAvailable(String new_dt){
        this.dates_available.add(this.dates_available.size(), new_dt);
    }
    public void removeDateAvailable(String new_dt){
        if (this.dates_available.contains(new_dt)){
            int index = this.dates_available.indexOf(new_dt);
            this.dates_available.remove(index);
        }
            
    }

    public List<Date> getDatesAvailable2(){
        return this.dates_available2;
    }
    public int getGroupsPerDate(){
        return this.groups_per_date;
    }
    public void setGroupsPerDate(int gpd){
        this.groups_per_date = gpd;
    }

    public int getSpotsPergroup(){
        return this.spots_per_group;
    }
    public void setSpotsPerGroup(int spg){
        this.spots_per_group = spg;
    }

    public String getLocation(){
        return this.location;
    }
    public void setLocation(String loc){
        this.location = loc;
    }

    public float getPrice(){
        return this.price;
    }
    public void setPrice(float pr){
        this.price = pr;
    }

    public float getRating(){
        return this.rating;
    }
    public void setRating(float rate){
        this.rating = rate;
    }

    public int getTimesBought(){
        return this.times_bought;
    }
    public void setTimesBought(int tb){
        this.times_bought = tb;
    }
   
   public String getFilesPath(){
        return this.filespath;
   }
   public void setFilePath(String fp){
        this.filespath = fp;
   }


}




