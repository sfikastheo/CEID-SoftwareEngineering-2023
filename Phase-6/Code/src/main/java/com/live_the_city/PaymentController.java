package com.live_the_city;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PaymentController {

    @FXML
    private TextField cardField;

    @FXML
    private TextField nameField;

    @FXML
    private Button payBut;

    @FXML
    private Label payammountLbl;

    private static Tour tour;


    @FXML
    private void initialize(){
        Float cost = tour.getPrice();
        payammountLbl.setText(Float.toString(cost));
    }

    @FXML
    public void setPaymentInfo(MouseEvent event){
        try{
            String card = cardField.getText();
            String name = nameField.getText();

            if(checkPaymentInfo(card, name)){
                User user = new User(1, "zisissour", "123456789", "zis@mail.gr");

                Payment payment = new Payment(tour.getPrice(), user, tour, "/home/zisissour/Desktop/");
                payment.executePayment();
                payment.send_receipt();
                payment.send_certificate();

                tour.updateStats(1);
                if(tour.getVirtual()){
                    System.out.println("Tranfering to Virtual Tour screen...");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }



    }



    public boolean checkPaymentInfo(String card, String name){
        try{
            int cardnum = Integer.parseInt(card);
            System.out.println(name + "'s payment info is accepted");
            return true;
        }
        catch(Exception e){
            e.printStackTrace();

            return false;
        }
    }

    public static void setTour(Tour t){
        tour=t;
    }

}
