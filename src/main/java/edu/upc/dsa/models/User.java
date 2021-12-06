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
    private Integer id;
    private Integer playerId;


    public Player getPlayer(){
        return (Player) SessionImpl.getInstance().get(Player.class, playerId);
    }

    public User() {}

    public User(String userName, String password, String fullName, String email) {
        this();
        this.userName=userName;
        this.password=password;
        this.fullName=fullName;
        this.email=email;
        this.status=false;
        //this.player= new Player(userName);
    }

    public User(Integer id, Integer playerId, String userName, String password) {
        this();
        this.userName=userName;
        this.password=password;
        this.id = id;
        this.fullName=null;
        this.email=null;
        this.status=false;
        this.playerId = playerId;
        //this.player = null;
        //this.player= new Player(userName);
    }

    /**********************************************************************
     **********************    Getters & Setters   ************************
     **********************************************************************/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

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
