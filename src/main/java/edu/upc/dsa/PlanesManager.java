package edu.upc.dsa;

import edu.upc.dsa.models.Insignia;
import edu.upc.dsa.models.InsigniaModel;
import edu.upc.dsa.models.Plane;
import edu.upc.dsa.models.PlaneModel;

import java.util.List;

public interface PlanesManager {
    PlaneModel getPlaneByModel(String model);
    List<PlaneModel> getAll();
    List<Plane> getAllFromPlayer(String playerName);
    void addPlaneToPlayer(String planeModel, String playerName);
    Boolean existPlaneModel(String planeModel);
    Boolean existPlayer(String playerName);
}
