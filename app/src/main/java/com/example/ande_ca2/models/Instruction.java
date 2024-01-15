package com.example.ande_ca2.models;

public class Instruction {
    private String imageUrl;
    private String description;

    // Constructor
    public Instruction(String imageUrl, String description) {
        this.imageUrl = imageUrl;
        this.description = description;
    }

    // Getters and setters
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
