package com.example.dicerollerxml

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var diceImage1: ImageView
    private lateinit var diceImage2: ImageView
    private lateinit var rollButton: Button
    private lateinit var historyTextView: TextView

    private val historyList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diceImage1 = findViewById(R.id.diceImage1)
        diceImage2 = findViewById(R.id.diceImage2)
        rollButton = findViewById(R.id.rollButton)
        historyTextView = findViewById(R.id.historyTextView)

        rollButton.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {

        // Menghasilkan angka dadu 1 sampai 6
        val dice1 = Random.nextInt(1, 7)
        val dice2 = Random.nextInt(1, 7)

        // Menentukan gambar dadu pertama
        val image1 = when (dice1) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        // Menentukan gambar dadu kedua
        val image2 = when (dice2) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        // Mengubah gambar dadu
        diceImage1.setImageResource(image1)
        diceImage2.setImageResource(image2)

        // Animasi sederhana
        diceImage1.animate()
            .rotationBy(360f)
            .setDuration(400)
            .start()

        diceImage2.animate()
            .rotationBy(360f)
            .setDuration(400)
            .start()

        // Menambahkan hasil ke riwayat
        historyList.add(0, "$dice1 + $dice2")

        // Menyimpan maksimal 5 riwayat
        if (historyList.size > 5) {
            historyList.removeAt(historyList.lastIndex)
        }

        // Menampilkan riwayat
        historyTextView.text = historyList.joinToString("\n") {
            "🎲  $it"
        }

        // Suara saat dadu dikocok
        val toneGenerator = ToneGenerator(
            AudioManager.STREAM_MUSIC,
            80
        )

        toneGenerator.startTone(
            ToneGenerator.TONE_PROP_BEEP,
            200
        )

        toneGenerator.release()
    }
}