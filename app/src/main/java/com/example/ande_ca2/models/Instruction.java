package com.example.ande_ca2.models;

import java.util.List;

public class Instruction {

    private String description;
    private List<String> imageUrls;

    // Constructor
    public Instruction(String description, List<String> imageUrls) {
        this.description = description;
        this.imageUrls = imageUrls;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Getter for image URLs
    public List<String> getImageUrls() {
        return imageUrls;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Setter for image URLs
    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
