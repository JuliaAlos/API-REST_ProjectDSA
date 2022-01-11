package edu.upc.dsa.models;

public class Insignia {
    private String id;
    private String data;
    private String name;
    private String playerId;

    public Insignia() {
    }

    public Insignia(String date, String name, String playerId) {
        this();
        this.data = date;
        this.name = name;
        this.playerId = playerId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**********************************************************************
     **********************    Getters & Setters   ************************
     **********************************************************************/



    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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