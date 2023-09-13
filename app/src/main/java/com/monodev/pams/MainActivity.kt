package com.monodev.pams

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.interaction.DragInteraction
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.monodev.pams.api.data.DataStoreManager
import com.monodev.pams.component.Component
import com.monodev.pams.component.onboarding.StartMenu
import com.monodev.pams.foreground.ForegroundService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val packagecontext = this

        val intent1 = Intent(this, ForegroundService::class.java)
        startForegroundService(intent1)

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

        var Startdest = ""
        val result = DataStoreManager(this).observeConfig()

        runBlocking {
            if(result.first() === 1){
                print(result.first())
                Startdest = "Home"
            } else {
                print(result.first())
                Startdest = "InitMenu"
            }
        }

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "InitMenu") {
                composable(route = "InitMenu") {
                    StartMenu().test(navController, packagecontext)
                }

                composable(route = "Home") {
                    Component().MainMenu(navController)
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
