package edu.upc.dsa.models;

import edu.upc.dsa.SessionImpl;

import javax.ws.rs.core.Link;
import java.util.*;

public class Player {
    private String id;
    private String playerName;
    private Integer maxDistance;
    private String rol;
    private Float timeOfFlight;
    private Integer bitcoins;

    public Player(){}
    public Player(String playerName){
        this();
        this.playerName=playerName;
        this.maxDistance=0;
        this.rol = "Cadet";
        this.timeOfFlight=0f;
        this.bitcoins=0;
    }

    public Player(String id, String playerName, Integer maxDistance, String rol, Float timeOfFlight, Integer bitcoins){
        this();
        this.id = id;
        this.playerName=playerName;
        this.maxDistance=maxDistance;
        this.rol = rol;
        this.timeOfFlight=timeOfFlight;
        this.bitcoins=bitcoins;
    }

    public List<Insignia> getInsigniaList(){
        List<Insignia> l = new LinkedList<>();
        SessionImpl.getInstance().findAll(Insignia.class, new HashMap<String, Object>(){{put("playerId",id);}}).forEach(el -> l.add((Insignia) el));
        return l;
    }

    public List<Plane> getPlaneList(){
        List<Plane> l = new LinkedList<>();
        SessionImpl.getInstance().findAll(Plane.class, new HashMap<String, Object>(){{put("playerId",id);}}).forEach(el -> l.add((Plane) el));
        return l;
    }

    public List<Upgrade> getUpgradesList(){
        List<Upgrade> l = new LinkedList<>();
        SessionImpl.getInstance().findAll(Upgrade.class, new HashMap<String, Object>(){{put("playerName",playerName);}}).forEach(el -> l.add((Upgrade) el));
        return l;
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

    public Float getTimeOfFlight() {
        return timeOfFlight;
    }

    public void setTimeOfFlight(Float timeOfFlight) {
        this.timeOfFlight = timeOfFlight;
    }

    public Integer getBitcoins() {
        return bitcoins;
    }

    public void setBitcoins(Integer bitcoins) {
        this.bitcoins = bitcoins;
    }

}

