package edu.upc.dsa.transferObjects;

public class RankingTO {
    private String userName;
    private String image_url;
    private String pos;
    private String score;

    public RankingTO(){}

    public RankingTO(String userName, String pos, String score,String image_url){
        this();
        this.userName=userName;
        this.pos=pos;
        this.score=score;
        this.image_url=image_url;
    }

    public String getUserName() {
        return userName;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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
