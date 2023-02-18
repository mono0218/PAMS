package com.monodev.PAMS

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TestForegroundReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val targetIntent = Intent(context, TestForegroundService::class.java)
        context.stopService(targetIntent)
    }
}