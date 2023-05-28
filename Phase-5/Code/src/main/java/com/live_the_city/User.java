package com.live_the_city;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;

    public User(int id, String usrn, String pass, String em){
        this.id = id;
        this.username = usrn;
        this.password = pass;
        this.email = em;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int newid){
        this.id = newid;
    }

    public String getUsername(){
        return this.username;
    }
    public void setUsername(String usrn){
        this.username = usrn;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String pass){
        this.password = pass;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String em){
        this.email = em;
    }
}
