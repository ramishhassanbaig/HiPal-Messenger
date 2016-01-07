package com.example.ramish.hipal_messenger.model;

/**
 * Created by ramish on 1/2/2016.
 */
public class Chat {
    private String chatName;
    private String Admin;
    private int messagesCount;
    private int membersCount;

    public Chat(String chatName, String admin) {
        this.chatName = chatName;
        Admin = admin;
    }

    public Chat(String chatName, String admin, int messagesCount, int membersCount) {
        this.chatName = chatName;
        Admin = admin;
        this.messagesCount = messagesCount;
        this.membersCount = membersCount;
    }

    public Chat(int messagesCount, int membersCount) {
        this.messagesCount = messagesCount;
        this.membersCount = membersCount;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getAdmin() {
        return Admin;
    }

    public void setAdmin(String admin) {
        Admin = admin;
    }

    public int getMessagesCount() {
        return messagesCount;
    }

    public void setMessagesCount(int messagesCount) {
        this.messagesCount = messagesCount;
    }

    public int getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(int membersCount) {
        this.membersCount = membersCount;
    }
}
