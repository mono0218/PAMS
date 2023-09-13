package com.monodev.pams
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.monodev.pams.foreground.ForegroundService
import com.monodev.pams.component.Component
import com.monodev.pams.component.onboarding.StartMenu

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "InitMenu") {
                composable(route="InitMenu"){
                    StartMenu().test(navController,applicationContext)
                }

                composable(route = "Home") {
                    Component().MainMenu(navController)
                }
                composable(route = "Notification") {
                    Component().NotificationMenuComponent(applicationContext)
                }
                composable(route="WebView"){
                    Component().BlogWebView()
                }
            }
        }
    }

}