package com.monodev.PAMS

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.monodev.pams.BuildConfig
import com.monodev.pams.R

class activity_3 : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)
        val btn = findViewById<Button>(R.id.btn)
        val btn1 = findViewById<Button>(R.id.setbtn)
        btn.setOnClickListener(this)
        btn1.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if(p0 == findViewById(R.id.btn)) {
            val view_intent = Intent(this,activity_4::class.java)
            startActivity(view_intent)
        }else{
            val intent1 = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                .putExtra("android.provider.extra.APP_PACKAGE", BuildConfig.APPLICATION_ID)
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent1)
        }
    }

}