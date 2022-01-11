package edu.upc.dsa.transferObjects;

import edu.upc.dsa.models.User;

public class RegisterUserTO {
    private String userName;
    private String password;
    private String fullName;
    private String email;
    private String image_url;

    public RegisterUserTO() {}

    public RegisterUserTO(String userName, String password, String fullName, String email,String image_url) {
        this();
        this.userName=userName;
        this.password=password;
        this.fullName=fullName;
        this.email=email;
        this.image_url=image_url;
    }

    public User toUser(){
        User u = new User();
        u.setUserName(this.userName);
        u.setPassword(this.password);
        u.setFullName(this.fullName);
        u.setEmail(this.email);
        u.setStatus(true);
        u.setImage_url(this.image_url);
        return u;
    }


    /**********************************************************************
     **********************    Getters & Setters   ************************
     **********************************************************************/
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
