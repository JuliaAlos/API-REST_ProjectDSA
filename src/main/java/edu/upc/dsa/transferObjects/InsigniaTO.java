package edu.upc.dsa.transferObjects;

import edu.upc.dsa.SessionImpl;
import edu.upc.dsa.models.Insignia;
import edu.upc.dsa.models.InsigniaModel;
import edu.upc.dsa.models.Player;

import java.util.HashMap;

public class InsigniaTO {

    private String date;
    private String playerName;
    private String name;
    private String type;

    public InsigniaTO() {
    }

    public InsigniaTO(Insignia insignia) {
        this();
        InsigniaModel insigniaModel = (InsigniaModel) SessionImpl.getInstance().findAll(InsigniaModel.class, new HashMap<String, Object>(){{put("name", insignia.getName());}}).get(0);
        Player p =(Player) SessionImpl.getInstance().get(Player.class, insignia.getPlayerId());
        this.date = insignia.getData();
        this.playerName = p.getPlayerName();
        this.name = insigniaModel.getName();
        this.type = insigniaModel.getType();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

