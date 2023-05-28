package com.live_the_city;

import java.time.LocalDateTime;
import java.util.List;

public class JOffer {
    private int id;
    private Host employer;
    private String title;
    private String description;
    private List<String> tags;
    private int num_employeees;
    private float salary;
    private LocalDateTime date_opened;
    private LocalDateTime expires;
    private String files_path;
    private boolean ispublic;
    
    public JOffer(int id, Host host, String title, String desc, List<String> tags, int emno, float sal, LocalDateTime dop, LocalDateTime exp, String path, boolean pbl ){
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

    public int getId(){
        return this.id;
    }
    public void setId(int newid){
        this.id = newid;
    }

    public Host getHost(){
        return this.employer;
    }
    public void setHost(Host newhost){
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
        this.title = newdesc;
    }

    public List<String> getTags(){
        return this.tags;
    }
    public void setTeags(List<String> newtags){
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

    public LocalDateTime getDateOpened(){
        return this.date_opened;
    } 
    public void setDateOpened(LocalDateTime newdo){
        this.date_opened = newdo;
    }

    public LocalDateTime getExpires(){
        return this.expires;
    } 
    public void setExpires(LocalDateTime newexp){
        this.date_opened = newexp;
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





    
}
