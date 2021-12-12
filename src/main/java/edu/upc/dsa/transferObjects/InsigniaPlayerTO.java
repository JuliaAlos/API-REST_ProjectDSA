package edu.upc.dsa.transferObjects;

public class InsigniaPlayerTO {
    private String playerName;
    private String insigniaName;

    InsigniaPlayerTO(){}

    public InsigniaPlayerTO(String playerName, String insigniaName) {
        this.playerName = playerName;
        this.insigniaName = insigniaName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setInsigniaName(String insigniaName) {
        this.insigniaName = insigniaName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getInsigniaName() {
        return insigniaName;
    }
}
