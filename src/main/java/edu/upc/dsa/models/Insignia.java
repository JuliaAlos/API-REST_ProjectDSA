package edu.upc.dsa.models;

public class Insignia {
    private String insigniaID;
    private String date;
    private String name;
    private String playerId;

    public Insignia() {
    }

    public Insignia(String insigniaID, String date, String name, String playerId) {
        this();
        this.insigniaID = insigniaID;
        this.date = date;
        this.name = name;
        this.playerId = playerId;
    }



    /**********************************************************************
     **********************    Getters & Setters   ************************
     **********************************************************************/

    public String getInsigniaID() {
        return insigniaID;
    }

    public void setInsigniaID(String insigniaID) {
        this.insigniaID = insigniaID;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}