package edu.upc.dsa.transferObjects;

public class RankingTO {
    private String userName;
    private String pos;
    private String score;

    public RankingTO(){}

    public RankingTO(String userName, String pos, String score){
        this();
        this.userName=userName;
        this.pos=pos;
        this.score=score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
