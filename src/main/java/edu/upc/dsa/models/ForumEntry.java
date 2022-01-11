package edu.upc.dsa.models;

public class ForumEntry {

    String id;
    String userName;
    String message;

    public ForumEntry(){}

    public ForumEntry(String id, String userName, String message) {
        this.id = id;
        this.userName = userName;
        this.message = message;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }
}
