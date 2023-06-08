package com.live_the_city;

import java.time.LocalDateTime;

public class Quiz {

    private int id;
    private String title;
    private String description;
    private LocalDateTime date_uploaded;
    private Tour on_tour;

    public Tour getOn_tour() {
        return on_tour;
    }
    public void setOn_tour(Tour on_tour) {
        this.on_tour = on_tour;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate_uploaded() {
        return date_uploaded;
    }
    public void setDate_uploaded(LocalDateTime date_uploaded) {
        this.date_uploaded = date_uploaded;
    }


    public Quiz(String title, String description, LocalDateTime upload_date) {
    
        this.title = title;
        this.description = description;
        this.date_uploaded = upload_date;
    }

    public Quiz() {
    }
}
