package com.fortumars.mart.model;

public class Product {
    private int id;
    private String name;
    private String category;
    private double price;
    private String description;
    private String image;
    private float rating;

    public Product(int id, String name, String category, double price, String description, String image, float rating) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.image = image;
        this.rating = rating;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getImage() { return image; }
    public float getRating() { return rating; }
}
