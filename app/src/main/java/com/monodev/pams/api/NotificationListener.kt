package com.monodev.pams.api

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationListener : NotificationListenerService() {
    override fun onNotificationPosted(notification: StatusBarNotification) {
        super.onNotificationPosted(notification)
        println()
        val extras = notification.notification.extras
        val message = notification.groupKey
        val test = message.contains("com.monodev.pams")
        if (!test) {
            val text = extras.getCharSequence(Notification.EXTRA_TEXT)
            Log.i("Notification Listener", text.toString())
            Classification().main(text.toString(),resources, this)
        }
    }
}