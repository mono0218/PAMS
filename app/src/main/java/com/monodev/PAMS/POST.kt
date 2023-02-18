package com.monodev.PAMS

import android.app.NotificationManager
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.monodev.pams.R
import java.io.IOException
import java.util.*

class POST(private val context: Context) : AsyncTask<String?, String?, String>() {
    override fun doInBackground(vararg text: String?): String {
        val _post = API()

        return try {
            val result = _post.Post(
                "https://oyster-app-gzdib.ondigitalocean.app/post/",
                "" + Arrays.toString(text) + ""
            )
            result
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    override fun onPostExecute(result: String) {
        if (result == "spam") {
            val builder = NotificationCompat.Builder(context, "default")
            builder.setSmallIcon(R.drawable.ic_launcher_foreground)
            builder.setContentTitle("PAMS")
            builder.setContentText("フィッシングメッセージを検知しました。\n注意してください.")
            builder.priority = NotificationCompat.PRIORITY_MAX
            builder.setCategory(NotificationCompat.CATEGORY_MESSAGE)
            builder.setChannelId("default")
            val notificationManager = NotificationManagerCompat.from(
                context
            )
            notificationManager.notify(1111, builder.build())
        }
    }
}