package edu.upc.dsa.models;

public class ForumEntry {

    String id;
    String userName;
    String message;
    Integer numSeq;

    public ForumEntry(){}

    public ForumEntry(String userName, String message, Integer numSeq) {
        this.userName = userName;
        this.message = message;
        this.numSeq = numSeq;
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

    public void setNumSeq(Integer numSeq) {
        this.numSeq = numSeq;
    }

    public Integer getNumSeq() {
        return numSeq;
    }
}
