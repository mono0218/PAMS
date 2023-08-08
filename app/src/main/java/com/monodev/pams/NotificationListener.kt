package com.monodev.pams

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationListener : NotificationListenerService() {
    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)
        val notification = sbn.notification
        val extras = sbn.notification.extras
        val message = sbn.groupKey
        val b1 = message.contains("com.monodev.pams")
        if (!b1) {
            val text = extras.getCharSequence(Notification.EXTRA_TEXT);
            Log.i("Notification Listener", text.toString())
            main(text.toString())
        }
    }

    companion object {
        const val TAG = "PAMS_listener"
    }
}