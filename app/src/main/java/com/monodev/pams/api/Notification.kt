package com.monodev.pams.api

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.monodev.pams.MainActivity
import com.monodev.pams.R

class Notification {

    fun send(applicationContext: Context) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.putExtra("fromNotification", true)

        // アクティビティを起動するPendingIntentオブジェクト
        val stopServiceIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        val builder = NotificationCompat.Builder(applicationContext,"default")
        builder.setSmallIcon(R.drawable.logo)
        builder.setContentTitle("PAMS")
        builder.priority = NotificationCompat.PRIORITY_MAX
        builder.setCategory(NotificationCompat.CATEGORY_MESSAGE)
        builder.setContentIntent(stopServiceIntent)
        builder.setStyle(NotificationCompat.BigTextStyle()
            .bigText("フィッシングメッセージを検知しました。\nタップして詳細をご確認ください"))
        val notificationManager = NotificationManagerCompat.from(applicationContext)
        notificationManager.notify(1111, builder.build())
        println("通知を送信しました")
    }
}