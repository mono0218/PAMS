package com.monodev.pams.API

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationListener : NotificationListenerService() {
    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)
        val extras = sbn.notification.extras
        val message = sbn.groupKey
        val b1 = message.contains("com.monodev.pams")
        if (!b1) {
            val text = extras.getCharSequence(Notification.EXTRA_TEXT);
            Log.i("Notification Listener", text.toString())
            Classification().main(text.toString(),resources, this)
        }
    }

    companion object {
        const val TAG = "PAMS_listener"
    }
}