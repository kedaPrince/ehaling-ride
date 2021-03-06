package com.example.hdriver.Services;

import androidx.annotation.NonNull;

import com.example.hdriver.Common;
import com.example.hdriver.Model.EventBus.DriverRequestReceived;
import com.example.hdriver.Utils.UserUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;
import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        if (FirebaseAuth.getInstance().getCurrentUser() !=null)
            UserUtils.updateToken(this,s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> dataRecv = remoteMessage.getData();
        if (dataRecv !=null){

            if (dataRecv.get(Common.NOTI_TITTLE).equals(Common.REQUEST_DRIVER_TITLE))
            {

                DriverRequestReceived driverRequestReceived = new DriverRequestReceived();
                driverRequestReceived.setKey(dataRecv.get(Common.RIDER_KEY));
                driverRequestReceived.setPickupLocation(dataRecv.get(Common.RIDER_PICKUP_LOCATION));
                driverRequestReceived.setPickupLocationString(dataRecv.get(Common.RIDER_PICKUP_LOCATION_STRING));
                driverRequestReceived.setDestinationLocation(dataRecv.get(Common.RIDER_DESTINATION));
                driverRequestReceived.setDestinationLocationString(dataRecv.get(Common.RIDER_DESTINATION_STRING));


                EventBus.getDefault().postSticky(driverRequestReceived);



            }
            else {
                Common.showNotification(this,new Random().nextInt(),
                        dataRecv.get(Common.NOTI_TITTLE),
                        dataRecv.get(Common.NOTI_CONTENT),
                        null);
           }


        }
    }
}
