package com.example.ande_ca2.models;

import java.util.Date;

public class Comment {
    private String description;
    private String authorName;
    private String authorImageUrl;
    private int likes;
    private Date datePosted;

    // Constructor
    public Comment(String description, String authorName, String authorImageUrl, int likes, Date datePosted) {
        this.description = description;
        this.authorName = authorName;
        this.authorImageUrl = authorImageUrl;
        this.likes = likes;
        this.datePosted = datePosted;
    }

    // Getters and setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorImageUrl() {
        return authorImageUrl;
    }

    public void setAuthorImageUrl(String authorImageUrl) {
        this.authorImageUrl = authorImageUrl;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }
}
