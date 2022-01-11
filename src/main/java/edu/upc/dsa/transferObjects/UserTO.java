package edu.upc.dsa.transferObjects;

import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.User;

public class UserTO {
    private String userName;
    private String fullName;
    private String email;
    private String image_url;
    private Boolean status;
    private Player player;

    public UserTO() {}

    public UserTO(User user) {
        this();
        this.userName = user.getUserName();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.status = user.getStatus();
        this.player = user.getPlayer();
        this.image_url = user.getImage_url();
    }

    public UserTO(String userName, String fullName, String email,Boolean status,Player player,String image_url) {
        this();
        this.userName=userName;
        this.fullName=fullName;
        this.email=email;
        this.status=status;
        this.player=player;
        this.image_url=image_url;
    }




    /**********************************************************************
     **********************    Getters & Setters   ************************
     **********************************************************************/
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
