package com.greensteam.objects;

public class Game {
    private String name;
    private int dowloadQuantity;
    private String description;
    private double reviewsPercentage;

    public Game(String name, int dowloadQuantity, String description, double reviewsPercentage) {
        this.name = name;
        this.dowloadQuantity = dowloadQuantity;
        this.description = description;
        this.reviewsPercentage = reviewsPercentage;
    }

    public String getDescription() {
        return description;
    }

    public int getDowloadQuantity() {
        return dowloadQuantity;
    }

    public String getName() {
        return name;
    }

    public double getReviewsPercentage() {
        return reviewsPercentage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDowloadQuantity(int dowloadQuantity) {
        this.dowloadQuantity = dowloadQuantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReviewsPercentage(double reviewsPercentage) {
        this.reviewsPercentage = reviewsPercentage;
    }

    public byte[] toByteArray() {
        return null;
    }

}
