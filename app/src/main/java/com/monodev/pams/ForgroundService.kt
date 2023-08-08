package com.monodev.pams
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.monodev.pams.R

class ForegroundService: Service(){
    companion object {
        const val CHANNEL_ID = "1234"
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("Service","PAMS foreground_service start")

        val channelId = CHANNEL_ID
        val channelName = "PAMS Channel"
        val channel = NotificationChannel(
            channelId, channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        )

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID )
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("PAMS動作中")
            .setContentText("フィッシング防止機能が動作しています")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        startForeground(2222, notification)

        return START_STICKY
    }

    override fun stopService(name: Intent?): Boolean {
        return super.stopService(name)
    }
}