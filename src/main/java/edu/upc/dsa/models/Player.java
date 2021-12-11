package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String id;
    private String playerName;
    private Integer maxDistance;
    private String rol;
    private Integer timeOfFlight;
    private Integer bitcoins;

    public List<Plane> getListPlanes(){
        return null;
    }

    public List<Insignia> getListInsignias(){
    return null;
    }

    public Player(){}
    public Player(String playerName){
        this();
        this.playerName=playerName;
        this.maxDistance=0;
        this.rol = "ROOKIE";
        this.timeOfFlight=0;
        this.bitcoins=0;
    }

    public Player(String id, String playerName, Integer maxDistance, String rol, Integer timeOfFlight, Integer bitcoins){
        this();
        this.id = id;
        this.playerName=playerName;
        this.maxDistance=maxDistance;
        this.rol = rol;
        this.timeOfFlight=timeOfFlight;
        this.bitcoins=bitcoins;
    }



    /**********************************************************************
     **********************    Getters & Setters   ************************
     **********************************************************************/
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(Integer maxDistance) {
        this.maxDistance = maxDistance;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getTimeOfFlight() {
        return timeOfFlight;
    }

    public void setTimeOfFlight(Integer timeOfFlight) {
        this.timeOfFlight = timeOfFlight;
    }

    public Integer getBitcoins() {
        return bitcoins;
    }

    public void setBitcoins(Integer bitcoins) {
        this.bitcoins = bitcoins;
    }

}

