package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoadingScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_screen)
        val prev = findViewById<Button>(R.id.button21)
        prev.setOnClickListener{
            finish()
        }
        val next = findViewById<Button>(R.id.button22)
        next.setOnClickListener{
            val intentTo3 = Intent(this, WorkingScreen::class.java)
            startActivity(intentTo3)
        }
    }
}