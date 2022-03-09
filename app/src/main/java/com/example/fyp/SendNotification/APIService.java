package com.example.fyp.SendNotification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAtrJyjG4:APA91bFUz5ayxbv6koLgzLsltXDBMB3-5J3ax91YQIGhtoe_0FLNCSeggWKOdhZR4GxWOMxwiTvkVQ1S0bnEzen0DztuKlpYn2KtfD2opzWTa9IPuXvRvYJcAksSWbnq0cXW7cn5Ma_0"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body NotificationSender body);
}
