package edu.upc.dsa.models;

public class Insignia {
    private String id;
    private String date;
    private String insigniaModelName;
    private String playerId;

    public Insignia() {
    }

    public Insignia(String date, String insigniaModelName, String playerId) {
        this();
        this.date = date;
        this.insigniaModelName = insigniaModelName;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInsigniaModelName() {
        return insigniaModelName;
    }

    public void setInsigniaModelName(String insigniaModelName) {
        this.insigniaModelName = insigniaModelName;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}