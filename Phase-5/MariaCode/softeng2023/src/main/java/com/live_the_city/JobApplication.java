package com.live_the_city;

import java.time.LocalDateTime;

public class JobApplication {

    private User applicant;
    //private JobOffer responding_to;
    private String fullname;
    private int phone;
    private String email;
    private String message;
    private LocalDateTime date_of_application;
    private String status;
    private String file_path;


    public JobApplication(User applicant, String fullname, int phone, String email, String message,
            LocalDateTime date_of_application, String file_path) {
        this.applicant = applicant;
        //this.respoonding_to = respoonding_to;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.message = message;
        this.date_of_application = date_of_application;
        this.file_path = file_path;
    }

    public JobApplication() {
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }
    
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    public int getPhone() {
        return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate_of_application() {
        return date_of_application;
    }
    public void setDate_of_application(LocalDateTime date_of_application) {
        this.date_of_application = date_of_application;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getFile_path() {
        return file_path;
    }
    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    /*public JobOffer getResponding_to() {
        return responding_to;
    }

    public void setResponding_to(JobOffer responding_to) {
        this.responding_to = responding_to;
    }*/


    
}
