package com.monodev.PAMS

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class MyNotificationListener : NotificationListenerService() {
    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)
        val notification = sbn.notification
        val extras = sbn.notification.extras
        val message = sbn.groupKey
        val b1 = message.contains("com.monodev.pams")
        val text = extras.getCharSequence(Notification.EXTRA_TEXT)
        if (b1 == false) {
            if(text is String){
                val myTask = POST(this)
                val _text = text.replace("[\n\r]".toRegex(), "")
                myTask.execute(_text as String?)
            }
        }
    }
}