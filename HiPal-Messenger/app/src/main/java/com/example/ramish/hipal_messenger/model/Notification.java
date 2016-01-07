package com.example.ramish.hipal_messenger.model;

/**
 * Created by ramish on 1/2/2016.
 */
public class Notification {
    private String from;
    private String NotifyMessage;

    public Notification(String from, String notifyMessage) {
        this.from = from;
        NotifyMessage = notifyMessage;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getNotifyMessage() {
        return NotifyMessage;
    }

    public void setNotifyMessage(String notifyMessage) {
        NotifyMessage = notifyMessage;
    }
}
