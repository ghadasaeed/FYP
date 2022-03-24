package com.example.fyp;

public class User {

    private String EmailId, Password,ConfirmPassword;

    public User(String emailId, String password, String confirmPassword) {
        EmailId = emailId;
        Password = password;
        ConfirmPassword = confirmPassword;
    }

    public User(){}

    public String getEmailId() {
        return EmailId;
    }
    public String getPassword() {
        return Password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }
}
