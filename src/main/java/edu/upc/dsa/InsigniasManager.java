package edu.upc.dsa;

import edu.upc.dsa.models.Insignia;
import edu.upc.dsa.models.InsigniaModel;

import java.util.List;

public interface InsigniasManager {
    List<InsigniaModel> getAll();
    List<Insignia> getAllFromPlayer(String playerName);
    void addInsigniaToPlayer(String idInsignia, String insigniaName, String playerName);
    Boolean existInsigniaModel(String insigniaName);
    Boolean existPlayer(String playerName);
}
