package com.samrudhasolutions.bolo

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioRecord
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.Settings
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.samrudhasolutions.bolo.algorithm.Algorithm
import com.samrudhasolutions.bolo.respository.ChatRepository
import com.samrudhasolutions.bolo.utils.ANSWERAFTEREDIT
import com.samrudhasolutions.bolo.utils.NetworkConnectivityObserver
import com.samrudhasolutions.bolo.utils.NetworkStatus
import kotlinx.coroutines.launch
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var ekharttd: ImageButton
    private lateinit var jghbjri: ImageButton
    private lateinit var kkhdff: ImageButton
    private lateinit var statusTextView: TextView
    private lateinit var statuscorrectedTextView: TextView
    private lateinit var generatingMarks: TextView
    private val networkConnectivityObserver: NetworkConnectivityObserver by lazy {
        NetworkConnectivityObserver(this)
    }

    private var audioRecord: AudioRecord? = null
    private var mediaPlayer: MediaPlayer? = null


    private val speechRecognizer: SpeechRecognizer by lazy {
        SpeechRecognizer.createSpeechRecognizer(this)
    }

    private val speechRecognizerIntent: Intent by lazy {
        Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
        }
    }


    private fun move(destinationActivity: Class<*>) {
        val intent = Intent(this, destinationActivity)
        startActivity(intent)
    }


    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ekharttd = findViewById(R.id.ekharttd)
        jghbjri = findViewById(R.id.jghbjri)
        kkhdff = findViewById(R.id.kkhdff)
        generatingMarks = findViewById(R.id.generatingMarks)
        statuscorrectedTextView = findViewById(R.id.statusTextView)
        val visualizerView = findViewById<VisualizerView>(R.id.visualizerView)


        val snackbar =
            Snackbar.make(
                findViewById(android.R.id.content),
                "No Internet Connection",
                Snackbar.LENGTH_INDEFINITE
            ).setAction("Wifi")
            {
                startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            }
        networkConnectivityObserver.observe(this) {
            when (it) {
                NetworkStatus.Available -> {
                    if (snackbar.isShown) {
                        snackbar.dismiss()
                    }
                }

                else -> {
                    snackbar.show()
                }
            }
        }

        statusTextView = findViewById(R.id.statusMicTextView)


        kkhdff.setOnClickListener {
            move(FirebaseActivity::class.java)

        }

        ekharttd = findViewById(R.id.ekharttd)

        ekharttd.setOnClickListener {
            convertAudioToText()
            initializeSpeechRecognizer()
        }


    }

    private fun initializeSpeechRecognizer() {
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {
                //here write the code for visualizer while input as rms
                val visualizerView by lazy { findViewById<VisualizerView>(R.id.visualizerView) }
                val amplitudes = FloatArray(1)

                amplitudes[0] = rmsdB


                visualizerView.updateVisualizer(amplitudes)
                Log.d("Visualizer", "RMS value: $rmsdB")

            }

            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onError(error: Int) {}

            @SuppressLint("SetTextI18n")
            override fun onResults(results: Bundle?) {
                generatingMarks(results)
            }


            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }

    @SuppressLint("SetTextI18n")
    fun generatingMarks(results: Bundle?) {
        val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        if (!matches.isNullOrEmpty()) {
            val recognizedText = matches[0]
            statusTextView.text = "Speech Recognized: $recognizedText"
            val myInstance = ChatRepository()
            lifecycleScope.launch {
                myInstance.createChatCompletion(
                    "Correct the grammar",
                    recognizedText
                )  // work to do
                statuscorrectedTextView.text = ANSWERAFTEREDIT

                val algo = Algorithm()
                val hululu = algo.calculateMarks(recognizedText, ANSWERAFTEREDIT)
                generatingMarks.text = hululu.toString()
            }
        }
    }


    private fun convertAudioToText() {
        // Check and request microphone permission if not granted
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                MICROPHONE_PERMISSION_REQUEST
            )
        } else {
            // Permission already granted, start listening for speech
            speechRecognizer.startListening(speechRecognizerIntent)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        audioRecord?.release()
        mediaPlayer?.release()
        speechRecognizer.destroy()
    }

    companion object {

        private const val MICROPHONE_PERMISSION_REQUEST = 123
    }
}
