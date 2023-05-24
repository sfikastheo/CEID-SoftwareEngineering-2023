package com.live_the_city;

import java.time.LocalDateTime;
import java.util.List;

public class VirtualTour extends Tour{
    private String vpath;

    public VirtualTour(int id, String title, List<String> tags, boolean virt, TourGuide tg, String desc, int mins, boolean pbl, LocalDateTime dtpbl, List<LocalDateTime> dtavail, int gpd, int spg, String loc, float price, float rate, int tb, String vp){
        super(id, title, tags, virt, tg, desc, mins, pbl, dtpbl, dtavail, gpd, spg, loc, price, rate, tb);
        this.vpath = vp;
    }
    
    public String getVpath(){
        return this.vpath;
    }
    public void setVpath(String vp){
        this.vpath = vp;
    }
}
