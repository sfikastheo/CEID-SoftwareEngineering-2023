package com.live_the_city;

public class TourGuide extends User{
    private String IBAN;

    public TourGuide(int id, String usrn, String pass, String em, String iban){
        super(id, usrn, pass, em);
        this.IBAN = iban;
    }
    
    public String getIBAN(){
        return this.IBAN;
    }
    public void setIBAN(String iban){
        this.IBAN = iban;
    }
}
