package com.monodev.pams.API
import ai.onnxruntime.OnnxTensor
import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession
import android.util.Log
import java.io.File



fun main (input:String){
    val ortEnvironment = OrtEnvironment.getEnvironment()
    val ortSession = createORTSession( ortEnvironment )
    val output = runPrediction( input , ortSession , ortEnvironment )
    Log.i("PAMS", output.toString())
}

private fun createORTSession( ortEnvironment: OrtEnvironment ) : OrtSession {
    return ortEnvironment.createSession( "file:///android_asset/monokamo.onnx")
}

private fun runPrediction( input : String , ortSession: OrtSession , ortEnvironment: OrtEnvironment ) : Float {
    val inputName = ortSession.inputNames?.iterator()?.next()

    val list = ArrayList<String>()
    File(input).forEachLine { list.add(it) }
    val _list = list.toTypedArray()

    val inputTensor = OnnxTensor.createTensor( ortEnvironment , _list , longArrayOf( 1, 1 ) )

    val results = ortSession.run( mapOf( inputName to inputTensor ) )

    val output = results[0].value as Array<FloatArray>
    return output[0][0]
}