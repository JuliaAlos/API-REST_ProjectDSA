package edu.upc.dsa;

import edu.upc.dsa.models.*;

import java.util.List;

public interface PlanesManager {
    PlaneModel getPlaneByModel(String model);
    List<PlaneModel> getAll();
    List<Plane> getAllFromPlayer(String playerName);
    void addPlaneToPlayer(String planeModel, String playerName);
    Boolean existPlaneModel(String planeModel);
    Boolean existPlayer(String playerName);
    void addUpgradeToPlayer (String modificationCode, String playerName, String planeModel);
    List<Upgrade> getAllUpgradesFromPlayer(String playerName);
}
