package com.monodev.pams
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.monodev.pams.API.Classtification
import com.monodev.pams.API.Database
import com.monodev.pams.API.Sql
import com.monodev.pams.Foreground.ForegroundService
import com.monodev.pams.component.MainMenu
import com.monodev.pams.component.NotificationMenuComponent
import com.monodev.pams.component.SettingsMenuComponent

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var db : Database.AppDatabase
        lateinit var dao : Sql.UserDao
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Database.dao.insertAll(Sql.History(uid = 0, content = "test", time = "test"))
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "Home") {
                composable(route = "Home") {
                    MainMenu(navController)
                }
                composable(route = "Notification") {
                    NotificationMenuComponent(navController)
                }
                composable(route = "Settings") {
                    SettingsMenuComponent(navController)
                }
            }
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
        println(Database.dao.getAll())
    }
}