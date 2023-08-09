package com.monodev.pams
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.res.AssetManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHost
import com.monodev.pams.Foreground.ForegroundService
import com.monodev.pams.component.MainMenu

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            MainMenu()
        }

        val intent = Intent(this, ForegroundService::class.java)
        startForegroundService(intent)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = "PAMS"
            val descriptionText = "PAMS"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel("default", name, importance)
            mChannel.description = descriptionText

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }
}