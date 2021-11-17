package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class User {
    private String userName;
    private String password;
    private String fullName;
    private String email;
    private Boolean status;
    private Player player;

    public User() {}

    public User(String userName, String password, String fullName, String email) {
        this();
        this.userName=userName;
        this.password=password;
        this.fullName=fullName;
        this.email=email;
        this.status=false;
        this.player= new Player(userName);
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


}
