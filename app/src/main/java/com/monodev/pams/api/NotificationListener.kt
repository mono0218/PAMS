package com.monodev.pams.api

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationListener : NotificationListenerService() {
    override fun onNotificationPosted(notification: StatusBarNotification) {
        super.onNotificationPosted(notification)
        val extras = notification.notification.extras
        val message = notification.groupKey
        val b1 = message.contains("com.monodev.pams")
        if (!b1) {
            val text = extras.getCharSequence(Notification.EXTRA_TEXT)
            Log.i("Notification Listener", text.toString())
            Classification().main(text.toString(),resources, this)
        }
    }
}