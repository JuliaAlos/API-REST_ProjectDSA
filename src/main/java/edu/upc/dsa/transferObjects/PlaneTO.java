package edu.upc.dsa.transferObjects;

import edu.upc.dsa.SessionImpl;
import edu.upc.dsa.models.InsigniaModel;
import edu.upc.dsa.models.Plane;
import edu.upc.dsa.models.PlaneModel;
import edu.upc.dsa.models.Player;

import java.util.HashMap;

public class PlaneTO {

    private String playerName;
    private String model;
    private Integer fuel, minFuel;
    private Integer enginesLife, maxEnginesLife;
    private Integer velX, maxSpeed;
    private Integer velY, maxManoeuvrability;
    private Double gravity, minWeight;

    public PlaneTO() {
    }

    public PlaneTO(Plane plane) {
        PlaneModel planeModel = (PlaneModel) SessionImpl.getInstance().findAll(PlaneModel.class, new HashMap<String, Object>(){{put("model", plane.getPlaneModelModel());}}).get(0);
        Player p =(Player) SessionImpl.getInstance().get(Player.class, plane.getPlayerId());
        this.model = plane.getPlaneModelModel();
        this.playerName = p.getPlayerName();
        this.fuel = planeModel.getFuel();
        this.minFuel = planeModel.getMinFuel();
        this.enginesLife = planeModel.getEnginesLife();
        this.maxEnginesLife = planeModel.getMaxEnginesLife();
        this.velX = planeModel.getVelX();
        this.maxSpeed = planeModel.getMaxSpeed();
        this.velY = planeModel.getVelY();
        this.maxManoeuvrability = planeModel.getMaxManoeuvrability();
        this.gravity = planeModel.getGravity();
        this.minWeight = planeModel.getMinWeight();
    }

    public String getModel() {
        return model;
    }

    public Integer getEnginesLife() {
        return enginesLife;
    }

    public Double getMinWeight() {
        return minWeight;
    }

    public Integer getMaxManoeuvrability() {
        return maxManoeuvrability;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public Integer getMaxEnginesLife() {
        return maxEnginesLife;
    }

    public Integer getMinFuel() {
        return minFuel;
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

    public void setMinWeight(Double minWeight) {
        this.minWeight = minWeight;
    }

    public void setMaxManoeuvrability(Integer maxManoeuvrability) {
        this.maxManoeuvrability = maxManoeuvrability;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setMaxEnginesLife(Integer maxEnginesLife) {
        this.maxEnginesLife = maxEnginesLife;
    }

    public void setMinFuel(Integer minFuel) {
        this.minFuel = minFuel;
    }
}
