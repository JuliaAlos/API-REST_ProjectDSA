package edu.upc.dsa.models;

public class Plane {

    private String id;
    private String planeModelModel;
    private String playerId;



    public Plane(){}

    public Plane(String planeModelModel, String playerId) {
        this();
        this.planeModelModel = planeModelModel;
        this.playerId = playerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaneModelModel() {
        return planeModelModel;
    }

    public void setPlaneModelModel(String planeModelModel) {
        this.planeModelModel = planeModelModel;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
