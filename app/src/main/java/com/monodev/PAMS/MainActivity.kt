package com.monodev.PAMS
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase
import com.monodev.pams.BuildConfig
import com.monodev.pams.R


class MainActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var data = getSharedPreferences("status", Context.MODE_PRIVATE)
        var Status = data.getBoolean("status",false)

        if(Status == false){
            setContentView(R.layout.activity_sub)
            val startbutton = findViewById<Button>(R.id.start)
            startbutton.setOnClickListener(this)
        }else{
            setContentView(R.layout.activity_main)
            val intent = Intent(this, TestForegroundService::class.java)
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

    override fun onClick(p0: View?) {
        if(p0 == findViewById(R.id.start)) {
            val view_intent = Intent(this,activity_1::class.java)
            startActivity(view_intent)
        }
    }
}
