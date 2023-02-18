package com.monodev.PAMS
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.monodev.pams.R

class notification : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Received ", Toast.LENGTH_LONG).show();

        val requestCode = intent.getIntExtra("RequestCode", 0)
        val pendingIntent = PendingIntent.getActivity(
            context, requestCode, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or
                    PendingIntent.FLAG_IMMUTABLE
        )
        val channelId = "default"

        // app name
        val title = "PAMS"

        var message = "フィッシングメッセージが検出されました。"
        // Notification　Channel 設定
        val channel = NotificationChannel(
            channelId, title,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = message
        val notificationManager =
            //                (NotificationManager)context.getSystemService(NotificationManager.class);
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.png)
            .setContentTitle("My notification")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        val notificationManagerCompat = NotificationManagerCompat.from(context)

        // 通知
        notificationManagerCompat.notify(R.string.app_name, builder.build())
    }
}