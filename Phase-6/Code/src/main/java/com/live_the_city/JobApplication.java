package com.live_the_city;

import java.time.LocalDateTime;

public class JobApplication {

    private User applicant;
    private JOffer responding_to;
    private String fullname;
    private String phone; //changed from int to string
    private String email;
    private String message;
    private LocalDateTime date_of_application;
    private String status;
    private String file_path;
    private String applicantName;


    public JobApplication(JOffer responding_to, String fullname, String phone, String email, String message,
            LocalDateTime date_of_application, String status, String file_path, String applicantName) {
        this.responding_to = responding_to;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.message = message;
        this.date_of_application = date_of_application;
        this.status = status;
        this.file_path = file_path;
        this.applicantName = applicantName;
    }


    public JobApplication(User applicant, JOffer responding_to, String fullname, String phone, String email, String message,
            LocalDateTime date_of_application, String file_path) {
        this.applicant = applicant;
        this.responding_to = responding_to;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.message = message;
        this.date_of_application = date_of_application;
        this.file_path = file_path;
    } //second constructor for functionality


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
    
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
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

    public JOffer getResponding_to() {
        return responding_to;
    }

    public void setResponding_to(JOffer responding_to) {
        this.responding_to = responding_to;
    }

    public String getApplicantName() {
        return applicantName;
    }


    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }
 
}
