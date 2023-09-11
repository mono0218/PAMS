package com.monodev.pams.API

import ai.onnxruntime.OnnxTensor
import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession
import android.content.res.Resources
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.monodev.pams.API.Database.AppDatabase
import com.monodev.pams.API.Database.AppDatabase.Companion.getDatabase
import com.monodev.pams.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Classtification(){
    companion object {
        lateinit var db : AppDatabase
        lateinit var dao : Sql.UserDao
    }
    fun main (input: String, resources: Resources, notificationListener: NotificationListener){

        val ortEnvironment = OrtEnvironment.getEnvironment()
        val ortSession = createSession(ortEnvironment,resources)
        val results = runClassification(input,ortSession,ortEnvironment) as Array<String>
        if ("spam" === results[0]){
            val builder = NotificationCompat.Builder(notificationListener, "default")
            builder.setSmallIcon(R.drawable.ic_launcher_foreground)
            builder.setContentTitle("PAMS")
            builder.setContentText("フィッシングメッセージを検知しました。\n注意してください.")
            builder.priority = NotificationCompat.PRIORITY_MAX
            builder.setCategory(NotificationCompat.CATEGORY_MESSAGE)
            builder.setChannelId("default")
            val notificationManager = NotificationManagerCompat.from(
                notificationListener
            )
            notificationManager.notify(1111, builder.build())

            db = getDatabase(notificationListener)
            dao = db.userDao()
            val _date = LocalDateTime.now()
            val dateformat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS E")
            val date = dateformat.format(_date)

            dao.insertAll(Sql.History(uid = 0, content = input, time = date.toString()))
        }
    }

    private fun createSession(ortEnvironment: OrtEnvironment, resources: Resources) : OrtSession {
        val model = resources.openRawResource(R.raw.monokamo).readBytes()
        return ortEnvironment.createSession(model)
    }

    private fun runClassification(input:String,ortSession:OrtSession,ortEnvironment:OrtEnvironment): Any? {
        val inputName = ortSession.inputNames?.iterator()?.next()

        val inputTensor = OnnxTensor.createTensor(ortEnvironment,arrayOf(input).toString())

        val results = ortSession.run(mapOf(inputName to inputTensor))

        val output = results[0].value
        return output
    }
}
