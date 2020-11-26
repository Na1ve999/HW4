package com.example.lab7_1kotlin

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private var rabprogress = 0
    private var turprogress = 0
    private lateinit var seekBar: SeekBar
    private lateinit var seekBar2: SeekBar
    private lateinit var btn_start: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seekBar = findViewById(R.id.seekBar)
        seekBar2 = findViewById(R.id.seekBar2)
        btn_start = findViewById(R.id.btn_start)
        btn_start.setOnClickListener{
            btn_start.isEnabled = false
            rabprogress = 0
            turprogress = 0
            seekBar.progress = 0
            seekBar2.progress = 0
            GlobalScope.launch {
                doTaskA()
            }
            GlobalScope.launch{
                doTaskB()
            }
        }
    }

    private suspend fun doTaskA() {
        while (rabprogress <= 100 && turprogress < 100) {
            try {
                delay(100)
                rabprogress += (Math.random() * 3).toInt()
                val msg = Message()
                msg.what = 1
                mHandler.sendMessage(msg)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }


    private val mHandler = Handler(Handler.Callback { msg ->
        when (msg.what) {
            1 -> seekBar.progress = rabprogress
        }
        if (rabprogress >= 100 && turprogress < 100) {
            Toast.makeText(this@MainActivity, "兔子勝利", Toast.LENGTH_SHORT).show()
            btn_start.isEnabled = true
        }
        false
    })

    private suspend fun doTaskB() {
        while (turprogress <= 100 && rabprogress < 100) {
            try {
                delay(200)
                turprogress += (Math.random() * 3).toInt()
                val msg1 = Message()
                msg1.what = 1
                mHandler2.sendMessage(msg1)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    private val mHandler2 = Handler(Handler.Callback { msg1 ->
        when (msg1.what) {
            1 -> seekBar2.progress = turprogress
        }
        if (turprogress >= 100 && rabprogress < 100) {
            Toast.makeText(this@MainActivity, "烏龜勝利", Toast.LENGTH_SHORT).show()
            btn_start.isEnabled = true
        }
        false
    })
}