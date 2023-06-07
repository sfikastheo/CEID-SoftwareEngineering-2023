package com.live_the_city;

public class Host extends User{
    private String IBAN;
    private String name;
    private String address;

    public Host(int id, String usrn, String pass, String em ,String iban, String nm, String addr){
        super(id, usrn, pass, em);
        this.IBAN = iban;
        this.name = nm;
        this.address = addr;
    }

    public String getIBAN(){
        return this.IBAN;
    }
    public void setIBAN(String iban){
        this.IBAN = iban;
    }
    
    public String getName(){
        return this.name;
    }
    public void setName(String nm){
        this.name = nm;
    }

    public String getAddress(){
        return this.address;
    }
    public void setAddress(String addr){
        this.address = addr;
    }
    
}
