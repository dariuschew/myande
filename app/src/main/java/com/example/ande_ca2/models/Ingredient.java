package com.example.ande_ca2.models;

public class Ingredient {
    private String name;
    private String img_url;

    public Ingredient(String name, String img_url) {
        this.name = name;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return img_url;
    }
}
