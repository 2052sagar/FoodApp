package com.edumbrella.foodapp.model;

/**
 * Created by Sagar Pandey on 5/21/2021.
 */
public class Popular {
    private String title, price, description;
    private int count;
    private int image;
    public Popular() {
    }

    public Popular(String title, String price, String description, int image, int count) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.image = image;
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
