package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val next = findViewById<Button>(R.id.button11)
        next.setOnClickListener{
            val intentTo2 = Intent(this, LoadingScreen::class.java)
            startActivity(intentTo2)
        }
        val settings = findViewById<Button>(R.id.button12)
        settings.setOnClickListener{
            val intentTo4 = Intent(this,SettingsScreen::class.java)
            startActivity(intentTo4)
        }
    }
}
