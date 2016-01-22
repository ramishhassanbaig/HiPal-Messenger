package com.example.ramish.hipal_messenger.model;

/**
 * Created by ramish on 1/2/2016.
 */
public class User {
    private String firstName;
    private String lastName;
    private int gender; // 0 for male ; 1 for female;
    private String dOB;
    private String email;
    private String password;
    private boolean online=false;
    private int friendCounter=0;
    private int favoritesCounter=0;
    private int friendReqCounter=0;
    private int notificationCounter=0;

    private String userName;

    public User(String firstName, String lastName, int gender, String dOB, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dOB = dOB;
        this.email = email;
        this.password = password;
        this.userName=firstName+" "+lastName;
    }

    public User(String firstName, String lastName, int gender, String dOB, String email, String password, boolean online) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dOB = dOB;
        this.email = email;
        this.password = password;
        this.online = online;
    }

    public User(int friendCounter, int favoritesCounter, int friendReqCounter, int notificationCounter) {
        this.friendCounter = friendCounter;
        this.favoritesCounter = favoritesCounter;
        this.friendReqCounter = friendReqCounter;
        this.notificationCounter = notificationCounter;
    }

    public User(String firstName, String lastName, int gender, String dOB, String email, String password, boolean online, int friendCounter, int favoritesCounter, int friendReqCounter, int notificationCounter) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dOB = dOB;
        this.email = email;
        this.password = password;
        this.online = online;
        this.friendCounter = friendCounter;
        this.favoritesCounter = favoritesCounter;
        this.friendReqCounter = friendReqCounter;
        this.notificationCounter = notificationCounter;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getdOB() {
        return dOB;
    }

    public void setdOB(String dOB) {
        this.dOB = dOB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public int getFriendCounter() {
        return friendCounter;
    }

    public void setFriendCounter(int friendCounter) {
        this.friendCounter = friendCounter;
    }

    public int getFavoritesCounter() {
        return favoritesCounter;
    }

    public void setFavoritesCounter(int favoritesCounter) {
        this.favoritesCounter = favoritesCounter;
    }

    public int getFriendReqCounter() {
        return friendReqCounter;
    }

    public void setFriendReqCounter(int friendReqCounter) {
        this.friendReqCounter = friendReqCounter;
    }

    public int getNotificationCounter() {
        return notificationCounter;
    }

    public void setNotificationCounter(int notificationCounter) {
        this.notificationCounter = notificationCounter;
    }
}
