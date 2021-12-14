package edu.upc.dsa.transferObjects;

import edu.upc.dsa.SessionImpl;
import edu.upc.dsa.models.InsigniaModel;
import edu.upc.dsa.models.Plane;
import edu.upc.dsa.models.PlaneModel;
import edu.upc.dsa.models.Player;

import java.util.HashMap;

public class PlaneTO {

    private String model;
    private String playerName;
    private Integer fuel;
    private Integer enginesLife;
    private Integer velX;
    private Integer velY;
    private Double gravity;

    public PlaneTO() {
    }

    public PlaneTO(Plane plane) {
        PlaneModel planeModel = (PlaneModel) SessionImpl.getInstance().findAll(PlaneModel.class, new HashMap<String, Object>(){{put("model", plane.getPlaneModelModel());}}).get(0);
        Player p =(Player) SessionImpl.getInstance().get(Player.class, plane.getPlayerId());
        this.model = plane.getPlaneModelModel();
        this.playerName = p.getPlayerName();
        this.fuel = planeModel.getFuel();
        this.enginesLife = planeModel.getEnginesLife();
        this.velX = planeModel.getVelX();
        this.velY = planeModel.getVelY();
        this.gravity = planeModel.getGravity();
    }

    public String getModel() {
        return model;
    }

    public Integer getEnginesLife() {
        return enginesLife;
    }

    public Integer getVelY() {
        return velY;
    }

    public Integer getVelX() {
        return velX;
    }

    public Double getGravity() {
        return gravity;
    }

    public Integer getFuel() {
        return fuel;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setFuel(Integer fuel) {
        this.fuel = fuel;
    }

    public void setEnginesLife(Integer enginesLife) {
        this.enginesLife = enginesLife;
    }

    public void setVelX(Integer velX) {
        this.velX = velX;
    }

    public void setVelY(Integer velY) {
        this.velY = velY;
    }

    public void setGravity(Double gravity) {
        this.gravity = gravity;
    }
}
