package io.demo;

import java.util.Date;

public class Ticket {

    String publisherEmail;
    String title;
    Date date;

    public Ticket(String publisherEmail, String title, Date date) {
        this.publisherEmail = publisherEmail;
        this.title = title;
        this.date = date;
    }

    public String getPublisherEmail() {
        return publisherEmail;
    }

    public void setPublisherEmail(String publisherEmail) {
        this.publisherEmail = publisherEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateInStringFormat(){
        String dateStringFormat = this.date.toString();
        return dateStringFormat;
    }
}
