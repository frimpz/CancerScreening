package com.drasare.cancerscreening.ui.guides;

public class Guides {

    String topic;
    String date;
    String url;
    int img;

    public Guides(String topic, String date, String url) {
        this.topic = topic;
        this.date = date;
        this.url = url;
    }

    public int getImg() {
        return img;
    }

    public Guides(String topic, String date, String url, int img) {
        this.topic = topic;
        this.date = date;
        this.url = url;
        this.img = img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
