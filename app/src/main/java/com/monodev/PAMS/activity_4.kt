package com.monodev.PAMS

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.monodev.pams.R


class activity_4 : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_4)
        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if(p0 == findViewById(R.id.btn)) {
            val sharedPref = getSharedPreferences("status", Context.MODE_PRIVATE)
            sharedPref.edit().putBoolean("status", true).apply()

            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
    }

}