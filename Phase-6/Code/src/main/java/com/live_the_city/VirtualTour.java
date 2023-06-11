package com.live_the_city;

import java.util.Date;
import java.util.List;

public class VirtualTour extends Tour{
    private String vpath;
	private String tourInfo;
	private String dates;
	private String bankAccount;
	private String template;

    public VirtualTour(int id, String title, List<String> tags, boolean virt, String tg, String desc, int mins, boolean pbl, Date dtpbl, List<String> dtavail, int gpd, int spg, String loc, float price, float rate, int tb, String vp){
        super(id, title, tags, virt, tg, desc, mins, pbl, dtpbl, dtavail, gpd, spg, loc, price, rate, tb);
        this.vpath = vp;
    }

	public VirtualTour(String title, String description, String dates, int spots_per_group, float price, String bankAccount, String TourInfo, String template, String media) {
		super(title, description, price, spots_per_group);
		this.dates = dates;
		this.bankAccount = bankAccount;
		this.tourInfo = TourInfo;
		this.template = template;
		this.vpath = media;
	}
	
    public String getVpath(){
        return this.vpath;
    }
    public void setVpath(String vp){
        this.vpath = vp;
    }
}
