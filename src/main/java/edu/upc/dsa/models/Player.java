package edu.upc.dsa.models;

import edu.upc.dsa.SessionImpl;

import javax.ws.rs.core.Link;
import java.util.*;

public class Player implements Comparable<Player> {
    private String id;
    private String playerName;
    private Integer maxDistance;
    private String rol;
    private Integer timeOfFlight;
    private Integer bitcoins;

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

    @Override
    public int compareTo(Player o) {
        List<String> hierarchy = new ArrayList<String>(){{
            add("Cadet");
            add("Second Officer");
            add("First Officer");
            add("Senior First Officer");
            add("Captain");
            add("Training Captain");
        }};




        return 0;
    }
}

