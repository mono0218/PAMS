package com.monodev.pams.Foreground

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ForegroundReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val targetIntent = Intent(context, ForegroundService::class.java)
        context.stopService(targetIntent)
    }
}