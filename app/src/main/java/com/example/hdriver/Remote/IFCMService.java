package com.example.hdriver.Remote;




import com.example.hdriver.Model.EventBus.FCMResponse;
import com.example.hdriver.Model.EventBus.FCMSendData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IFCMService {
    @Headers({
            "content-Type:application/json",
            "Authorization:key=AAAADFUCEDg:APA91bGtIgRNWDAlyW4JkYIP3xuI7e3es0utqS4WKHeZqJq4e09cpUvAB_YGKEatQP1QSVPYB9OOGgcEALT4NlLZ1DWKQAoXCchomwRKiax_efQSpreWS8MAAronm6HH5VkhDfvffeCl"
    })
    @POST("fcm/send")
    Observable<FCMResponse> sendNotification(@Body FCMSendData body);
}
