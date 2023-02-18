package com.monodev.PAMS;
import android.app.Notification;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;


public class MyNotificationListener extends NotificationListenerService {
    public final static String TAG = "PAMS_listener";

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        Notification notification = sbn.getNotification();
        Bundle extras = sbn.getNotification().extras;
        String message = sbn.getGroupKey();
        boolean b1 = message.contains("com.monodev.pams");

        if(b1 == false) {
            CharSequence text = extras.getCharSequence(Notification.EXTRA_TEXT);
            POST myTask = new POST(this);
            myTask.execute((String) text);
        }
    }
}


