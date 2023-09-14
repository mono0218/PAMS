package com.monodev.pams

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.monodev.pams.api.data.DataStoreManager
import com.monodev.pams.component.Component
import com.monodev.pams.component.onboarding.StartMenu
import com.monodev.pams.foreground.ForegroundService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fromNotification = intent.getBooleanExtra("fromNotification", false)
        if(fromNotification === true){
            setContent {
                Component().NotificationMenuComponent(applicationContext)
            }
        }else{
            val packagecontext = this

            var startDest = String()
            var result = 0

            val intent1 = Intent(this, ForegroundService::class.java)
            startForegroundService(intent1)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Create the NotificationChannel
                val name = "PAMS"
                val descriptionText = "PAMSが警告通知を出す際に使用します"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val mChannel = NotificationChannel("default", name, importance)
                mChannel.description = descriptionText

                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(mChannel)

            }

            runBlocking {
                val data = DataStoreManager(applicationContext).observeConfig()
                result = data.first()!!
            }
            startDest = if(result === 1){
                "Home"
            } else {
                "InitMenu"
            }

            setContent {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = startDest) {
                    composable(route = "InitMenu") {
                        StartMenu().test(navController, packagecontext)
                    }

                    composable(route = "Home") {
                        Component().MainMenu(navController,applicationContext)
                    }
                    composable(route = "Notification") {
                        Component().NotificationMenuComponent(applicationContext)
                    }
                    composable(route = "WebView") {
                        Component().BlogWebView()
                    }
                }
            }
        }
    }
    fun restart(restartcontext:Context){
        val intent1 = Intent(restartcontext, ForegroundService::class.java)
        restartcontext.stopService(intent1)
        restartcontext.startForegroundService(intent1)
    }
}
