package com.actvn.shopapp.api.model;

public class Phone {
    private String imgUrl;
    private String title,cost;

    public Phone(){

    }

    public Phone(String imgUrl, String title, String cost) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.cost = cost;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
