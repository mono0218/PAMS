package com.monodev.PAMS

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.monodev.pams.R

class activity_1_5_5 : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1_5_5)
        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if(p0 == findViewById(R.id.btn)) {
            val view_intent = Intent(this,activity_2::class.java)
            view_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(view_intent)
        }
    }

}