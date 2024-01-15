package com.example.ande_ca2.models;

import com.example.ande_ca2.adapters.IngredientsAdapter;

import java.util.List;

public class Recipe {
    private List<String> imageUrls;
    private String title;
    private String author;
    private String description;
    private String cookTime;
    private int servings;
    private String origin;
    private List<Ingredient> ingredients;
    private List<Instruction> instructions;
    private List<Comment> comments;
    private double rating;

    // Constructor
    public Recipe(List<String> imageUrls, String title, String author, String description,
                  String cookTime, int servings, String origin, List<Ingredient> ingredients,
                  List<Instruction> instructions, List<Comment> comments , double rating) {
        this.imageUrls = imageUrls;
        this.title = title;
        this.author = author;
        this.description = description;
        this.cookTime = cookTime;
        this.servings = servings;
        this.origin = origin;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.comments = comments;
        this.rating = rating;
    }

    // Getters and Setters
    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
