package com.elham.plato;

import java.util.Date;

public class Message {
    private String sender;
    private String receiver;
    private String body;
    private Date sentDate;

    public Message(String sender, String body) {
        this.sender = sender;
        this.body = body;
        this.sentDate = new Date();
    }

    public String getSender() {
        return sender;
    }

    public String getBody() {
        return body;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
