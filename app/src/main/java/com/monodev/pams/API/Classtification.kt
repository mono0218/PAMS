package com.monodev.pams.API

import ai.onnxruntime.OnnxTensor
import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession
import android.content.res.Resources
import com.monodev.pams.R

fun main (input: String, resources: Resources){
    val ortEnvironment = OrtEnvironment.getEnvironment()
    val ortSession = createSession(ortEnvironment,resources)
    val results = runClassification(input,ortSession,ortEnvironment) as Array<String>
    if ("spam" === results[0]){
        /*TODO*/
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