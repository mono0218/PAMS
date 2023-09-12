package com.monodev.pams.api

import ai.onnxruntime.OnnxTensor
import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession
import android.content.Context
import android.content.res.Resources
import androidx.room.Room
import com.monodev.pams.api.data.AppDao
import com.monodev.pams.api.data.AppDatabase
import com.monodev.pams.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Classification{
    fun main (input: String, resources: Resources, applicationContext: Context){
        val ortEnvironment = OrtEnvironment.getEnvironment()
        val ortSession = createSession(ortEnvironment,resources)
        val results = runClassification(input,ortSession,ortEnvironment) as Array<*>
        val status = results[0].toString()

        if ("spam" == status){
            Notification().send(applicationContext)
            val date = LocalDateTime.now()
            val dateformat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS")
            val time = dateformat.format(date)

            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).fallbackToDestructiveMigration().build()

            val userDao = db.userDao()
            val dataWoker = Thread {
                userDao.insertAll(AppDao.History(uid = 0, content = input, time = time.toString()))
            }
            dataWoker.start()
        }
    }

    private fun createSession(ortEnvironment: OrtEnvironment, resources: Resources) : OrtSession {
        val model = resources.openRawResource(R.raw.phissing).readBytes()
        return ortEnvironment.createSession(model)
    }

    private fun runClassification(input:String,ortSession:OrtSession,ortEnvironment:OrtEnvironment): Any? {
        val inputName = ortSession.inputNames?.iterator()?.next()

        val inputTensor = OnnxTensor.createTensor(ortEnvironment,arrayOf(input))

        val results = ortSession.run(mapOf(inputName to inputTensor))
        return results[0].value
    }
}
