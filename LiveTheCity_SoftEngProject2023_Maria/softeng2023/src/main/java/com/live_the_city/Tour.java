package com.live_the_city;

import java.time.LocalDateTime;
import java.util.List;

public class Tour {
    //ATTRIBUTES
    private int id;
    private String title;
    private List<String> tags;
    private boolean virtual;
    private TourGuide offered_by;
    private String description;
    private int duration;
    private boolean ispublic; 
    private LocalDateTime date_published;
    private List<LocalDateTime> dates_available;
    private int groups_per_date;
    private int spots_per_group;
    private String location;
    private float price;
    private float rating;
    private int times_bought;

    //METHODS
    public Tour(int id, String title, List<String> tags, boolean virt, TourGuide tg, String desc, int mins, boolean pbl, LocalDateTime dtpbl, List<LocalDateTime> dtavail, int gpd, int spg, String loc, float price, float rate, int tb)
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
    public void setId(boolean virt){
        this.virtual = virt;
    }
    
    public TourGuide getTourGuide(){
        return this.offered_by;
    }
    public void setTourGuide(TourGuide tg){
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

    public LocalDateTime getDatePublished(){
        return this.date_published;
    }
    public void setDatePublished(LocalDateTime dtpbl){
        this.date_published = dtpbl;
    }

    public List<LocalDateTime> getDatesAvailable(){
        return this.dates_available;
    }
    public void setDatesAvailable(List<LocalDateTime> dtavail){
        this.dates_available = dtavail;
    }
    public void addDateAvailable(LocalDateTime new_dt){
        this.dates_available.add(this.dates_available.size(), new_dt);
    }
    public void removeDateAvailable(LocalDateTime new_dt){
        if (this.dates_available.contains(new_dt)){
            int index = this.dates_available.indexOf(new_dt);
            this.dates_available.remove(index);
        }
            
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
   

   


}




