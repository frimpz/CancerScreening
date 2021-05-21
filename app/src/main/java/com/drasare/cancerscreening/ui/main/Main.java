package com.drasare.cancerscreening.ui.main;

public class Main {

    String title;
    int img;

    public Main(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public Main(String topic, int img) {
        this.title = topic;
        this.img = img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
