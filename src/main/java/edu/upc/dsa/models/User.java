package edu.upc.dsa.models;

import edu.upc.dsa.SessionImpl;
import edu.upc.dsa.util.QueryHelper;
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
    private String id;
    //one to one
    private String playerId;


    public Player getPlayer(){
        return (Player) SessionImpl.getInstance().get(Player.class, playerId);
    }

    public User() {}


    public User(String id, String playerId, String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.id = id;
        this.playerId = playerId;
        this.status = false;
        this.fullName=null;
        this.email=null;
    }

    public User(String playerId, String userName, String password) {
        this();
        this.userName=userName;
        this.password=password;
        //this.id = id;
        this.fullName=null;
        this.email=null;
        this.status=false;
        this.playerId = playerId;
        //this.player = null;
        //this.player= new Player(userName);
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
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



}
