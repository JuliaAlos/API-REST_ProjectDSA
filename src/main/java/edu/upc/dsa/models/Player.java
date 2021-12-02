package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String playerName;
    private Integer maxDistance;
    private String rol;
    private Integer timeOfFlight;
    private Integer bitcoins;
    private List<Plane> listPlanes = new ArrayList<>();
    private List<Insignia> listInsignias = new ArrayList<>();

    public Player(){}
    public Player(String playerName){
        this();
        this.playerName=playerName;
        this.maxDistance=0;
        this.rol = "Beginner";
        this.timeOfFlight=0;
        this.bitcoins=0;
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

    public List<Insignia> getListInsignias() {
        return listInsignias;
    }

    public List<Plane> getListPlanes() {
        return listPlanes;
    }

    /**********************************************************************
     **********************************************************************/

    public void addPlane(Plane plane){
        this.listPlanes.add(plane);
    }

    public void addInsignia (Insignia insignia){
        this.listInsignias.add(insignia);
    }
}

