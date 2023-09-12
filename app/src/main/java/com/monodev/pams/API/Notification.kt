package com.monodev.pams.API

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.monodev.pams.R

class Notification() {
    fun send(applicationContext: Context) {
        val builder = NotificationCompat.Builder(applicationContext)
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
        builder.setContentTitle("PAMS")
        builder.setContentText("フィッシングメッセージを検知しました。\n注意してください.")
        builder.priority = NotificationCompat.PRIORITY_MAX
        builder.setCategory(NotificationCompat.CATEGORY_MESSAGE)
        builder.setChannelId("default")
        val notificationManager = NotificationManagerCompat.from(applicationContext)
        notificationManager.notify(1111, builder.build())
        println("通知を送信しました")
    }
}