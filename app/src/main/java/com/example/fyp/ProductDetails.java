package com.example.fyp;

public class ProductDetails {

    public String  ProductCategory, ProductName, ProductBarCode, ProductEXP, AlarmState,ImageURL, RandomUID, UserId,ProductShelfLife;

    public ProductDetails(String  productCategory,String productName, String productBarCode, String productEXP, String alarmState, String imageURL, String randomUID, String userId, String productShelfLife) {

        ProductCategory =  productCategory;
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
