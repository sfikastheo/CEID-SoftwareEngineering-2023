package com.live_the_city;

public class Participation {
    
    private User user;
    private Tour tour=null;
    private Event event=null;

    public Participation(User user, Tour tour) {
        this.tour=tour;
        this.user=user;
    }

    public Participation(User user,Event event) {
        this.event=event;
        this.user=user;
    }

    public void setUser(User user){
        this.user=user;
    }

    public void setTour(Tour tour) {
        this.tour=tour;
    }

    public void setEvent(Event event) {
        this.event=event;
    }

    public User getUser() {
        return this.user;
    }

    public Tour getTour() {
        return this.tour;
    }

    public Event getEvent() {
        return this.event;
    }

}
