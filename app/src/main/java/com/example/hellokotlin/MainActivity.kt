package com.example.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import java.util.*


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this, this )

        findViewById<Button>(R.id.btnPlay).setOnClickListener { speak() }

    }

    private fun speak(){
        var message: String = findViewById<TextView>(R.id.etMessage).text.toString()

        if (message.isEmpty()){
            findViewById<TextView>(R.id.tvStatus).text = "Introduce un texto!"
            message = "Introduzca un texto, por favor"
        }

        tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")

    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS)
        {
            findViewById<TextView>(R.id.tvStatus).text = "Listo!"
            tts!!.language = Locale("ES")
        }
        else{
            findViewById<TextView>(R.id.tvStatus).text = "No Disponible!!"
        }

    }

    override fun onDestroy() {
        if (tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}