package edu.upc.dsa.transferObjects;

public class GameResultsTO {
    private Integer collectedBitcoins;
    private Integer distance;
    private Integer timeOfFlight;

    public GameResultsTO() {
    }

    public Integer getCollectedBitcoins() {
        return collectedBitcoins;
    }

    public void setCollectedBitcoins(Integer collectedBitcoins) {
        this.collectedBitcoins = collectedBitcoins;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getTimeOfFlight() {
        return timeOfFlight;
    }

    public void setTimeOfFlight(Integer timeOfFlight) {
        this.timeOfFlight = timeOfFlight;
    }
}
