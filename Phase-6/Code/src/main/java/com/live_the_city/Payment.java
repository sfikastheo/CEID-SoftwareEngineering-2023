package com.live_the_city;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class Payment {
    
    private float amount;
    private User user;
    private Tour tour;
    private String receipt_string;

    public Payment(float amount, User user, Tour tour, String receipt_string){
        this.amount = amount;
        this.user = user;
        this.tour = tour;
        this.receipt_string = receipt_string;
    }

    public boolean executePayment(){
        System.out.println("Contacting user's card issuer...");
   
        System.out.println("Payment executed successfully!"); 
        return true;
    }

    public void send_receipt(){
        
        try{
            File receipt = new File(receipt_string+"receipt.txt");
            FileWriter writer = new FileWriter(receipt);
            writer.write("---User's receipt---\nUsername: "+ user.getUsername()+ "\n Tour: " + tour.getTitle() + "\nAmmount: " + Float.toString(amount));
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        

    }

    public void send_certificate(){
        
        try{
            File receipt = new File(receipt_string+"certificate.txt");
            FileWriter writer = new FileWriter(receipt);
            writer.write("---User's Certificate---\nThis is to certify that the user participated in the tour mentioned below\nUsername: "+ user.getUsername()+ "\n Tour: " + tour.getTitle() + "\n Thank you for participating!");
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        

    }


}
