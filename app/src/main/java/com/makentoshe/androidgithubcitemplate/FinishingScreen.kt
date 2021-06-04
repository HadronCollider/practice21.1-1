package com.makentoshe.androidgithubcitemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FinishingScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finishing_screen)
        val prev = findViewById<Button>(R.id.button51)
        prev.setOnClickListener{
            finish()
        }
        val save = findViewById<Button>(R.id.button52)
    }
}