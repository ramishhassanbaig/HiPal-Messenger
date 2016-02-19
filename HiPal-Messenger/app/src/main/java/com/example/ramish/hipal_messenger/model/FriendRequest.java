package com.example.ramish.hipal_messenger.model;

/**
 * Created by ramish on 2/20/2016.
 */
public class FriendRequest {
    String name;
    boolean accepted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
