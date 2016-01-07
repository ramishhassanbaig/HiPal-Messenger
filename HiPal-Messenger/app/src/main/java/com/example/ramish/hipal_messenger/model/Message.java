package com.example.ramish.hipal_messenger.model;

import java.util.Calendar;

/**
 * Created by ramish on 1/2/2016.
 */
public class Message {
    private String sender;
    private String receiver;
    private String messageText;
    private Calendar timeStamp;


    public Message(String sender, String receiver, String messageText) {
        this.sender = sender;
        this.receiver = receiver;
        this.messageText = messageText;
    }

    public Message(String sender, String receiver, String messageText, Calendar timeStamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.messageText = messageText;
        this.timeStamp = timeStamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Calendar getTimeStamp() {
        return timeStamp;
    }

}
