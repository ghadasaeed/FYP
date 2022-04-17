package com.example.fyp.product;

public class UpdateProductModel {

    String RandomUID, UserId, ImageURL, ProductName, ProductEXP, ProductShelfLife, ProductBarCode, AlarmState;

    public UpdateProductModel(){}

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

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductEXP() {
        return ProductEXP;
    }

    public void setProductEXP(String productEXP) {
        ProductEXP = productEXP;
    }

    public String getProductShelfLife() {
        return ProductShelfLife;
    }

    public void setProductShelfLife(String productShelfLife) {
        ProductShelfLife = productShelfLife;
    }

    public String getProductBarCode() {
        return ProductBarCode;
    }

    public void setProductBarCode(String productBarCode) {
        ProductBarCode = productBarCode;
    }

    public String getAlarmState() {
        return AlarmState;
    }

    public void setAlarmState(String alarmState) {
        AlarmState = alarmState;
    }
}
