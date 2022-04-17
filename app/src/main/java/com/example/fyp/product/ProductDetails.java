package com.example.fyp.product;

public class ProductDetails {

    private String  ProductName, ProductBarCode, ProductEXP, AlarmState,ImageURL, RandomUID, UserId,ProductShelfLife;

    public ProductDetails(String productName, String productBarCode, String productEXP, String alarmState,String productShelfLife, String imageURL, String randomUID, String userId) {

        ProductName = productName;
        ProductBarCode = productBarCode;
        ProductEXP = productEXP;
        AlarmState = alarmState;
        ProductShelfLife = productShelfLife;
        ImageURL = imageURL;
        RandomUID = randomUID;
        UserId = userId;
        //may need to add category ID
    }

    public ProductDetails(){}

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductBarCode() {
        return ProductBarCode;
    }

    public void setProductBarCode(String productBarCode) {
        ProductBarCode = productBarCode;
    }

    public String getProductEXP() {
        return ProductEXP;
    }

    public void setProductEXP(String productEXP) {
        ProductEXP = productEXP;
    }

    public String getAlarmState() {
        return AlarmState;
    }

    public void setAlarmState(String alarmState) {
        AlarmState = alarmState;
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

    public String getProductShelfLife() {
        return ProductShelfLife;
    }

    public void setProductShelfLife(String productShelfLife) {
        ProductShelfLife = productShelfLife;
    }
}
