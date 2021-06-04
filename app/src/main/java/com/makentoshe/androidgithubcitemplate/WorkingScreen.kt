package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WorkingScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_working_screen)
        val prev = findViewById<Button>(R.id.button31)
        prev.setOnClickListener{
            finish()
        }
        val next = findViewById<Button>(R.id.button32)
        next.setOnClickListener{
            val intentTo5 = Intent(this, FinishingScreen::class.java)
            startActivity(intentTo5)
        }
    }
}