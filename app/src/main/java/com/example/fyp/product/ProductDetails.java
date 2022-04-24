package com.example.fyp.product;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ProductDetails {

    private String  ProductName, ProductBarCode, ProductEXP, AlarmState,ImageURL, RandomUID, UserId,ProductShelfLife;
    private String status;
    private int daysUntilExpired;
    private Calendar expiryDay;


    public ProductDetails(String productName, String productBarCode, String productEXP,
                          String alarmState,String productShelfLife, String imageURL, String randomUID, String userId) {

        ProductName = productName;
        ProductBarCode = productBarCode;
        ProductEXP = productEXP;
        AlarmState = alarmState;
        ProductShelfLife = productShelfLife;
        ImageURL = imageURL;
        RandomUID = randomUID;
        UserId = userId;
        recalculateDaysUntilExpired();
    }

//    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//    Date date = sdf.parse(ProductEXP);
//    Calendar cal = Calendar.getInstance();
//    cal.setTime(date);

//    public Calendar getExpiryDay() {
//        return expiryDay;
//    }
//
//    public void setExpiryDay(Calendar expiryDay) {
//        this.expiryDay = expiryDay;
//    }
    public ProductDetails(){}





    private Date stringToDate(String aDate) {

        if(aDate==null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat("dd/MM/yyyy");
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;

    }
//
    public void recalculateDaysUntilExpired() {
       // SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date toDate = stringToDate(ProductEXP);

        Date  fromDate = stringToDate(getTodaysDate());

        long diff = toDate.getTime() - fromDate.getTime();
        this.ProductEXP = String.valueOf(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));



//       // double timeBetweenDates = expiryDay.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
//        double timeBetweenDates = ProductEXP.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
//        this.daysUntilExpired = (int) Math.ceil((timeBetweenDates / 1000 / 60 / 60 / 24));
    }
        private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);

    }
    private String makeDateString(int day, int month, int year)
    {
        return day + "/" + month + "/" + year;
    }
//
//    public int getDaysUntilExpired() {
//        return daysUntilExpired;
//    }
//
//    public void setDaysUntilExpired(int daysUntilExpired) {
//        this.daysUntilExpired = daysUntilExpired;
//    }

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

    public String getAlarmState() { return AlarmState; }

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
