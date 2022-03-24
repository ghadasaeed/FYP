package com.example.fyp;

public class ProductDetails {

    public String   ProductName, ProductBarCode, ProductEXP, AlarmState,ImageURL, RandomUID, UserId,ProductShelfLife;

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

}
