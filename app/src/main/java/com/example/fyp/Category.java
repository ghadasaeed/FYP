package com.example.fyp;

public class Category {

    public Category(String categoryName, String imageURL, String randomUID, String userId) {

        CategoryName = categoryName;
        ImageURL = imageURL;
        RandomUID = randomUID;
        UserId = userId;

    }
    public Category() { }

    private String CategoryName, ImageURL, RandomUID, UserId;

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getRandomUID() {
        return RandomUID;
    }

    public void setRandomUID(String randomUID) {
        RandomUID = randomUID;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }




}
