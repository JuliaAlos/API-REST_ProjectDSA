package edu.upc.dsa.models;

import edu.upc.dsa.SessionImpl;
import edu.upc.dsa.util.QueryHelper;
import edu.upc.dsa.util.RandomUtils;

import java.util.*;

public class User implements Comparable<User>{
    private String userName;
    private String password;
    private String fullName;
    private String email;
    private String image_url;
    private Boolean status;
    private String id;
    //one to one
    private String playerId;


    public Player getPlayer(){
        return (Player) SessionImpl.getInstance().get(Player.class, playerId);
    }

    public User() {}

    public User(String id, String playerId, String userName, String password,String image_url) {
        this.userName = userName;
        this.password = password;
        this.id = id;
        this.image_url=image_url;
        this.playerId = playerId;
        this.status = true;
        this.fullName=null;
        this.email=null;
    }

    public User(String playerId, String userName, String password) {
        this();
        this.userName=userName;
        this.password=password;
        this.fullName=null;
        this.email=null;
        this.image_url=null;
        this.status=true;
        this.playerId = playerId;
    }

    /**********************************************************************
     **********************    Getters & Setters   ************************
     **********************************************************************/

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


    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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

    @Override
    public int compareTo(User o) {
        List<String> hierarchy = new ArrayList<String>(){{
            add("Cadet");
            add("Second Officer");
            add("First Officer");
            add("Senior First Officer");
            add("Captain");
            add("Training Captain");
        }};

        return Integer.compare(hierarchy.indexOf(o.getPlayer().getRol()), hierarchy.indexOf(this.getPlayer().getRol()));
    }

}
