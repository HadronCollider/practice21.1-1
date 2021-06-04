package com.makentoshe.androidgithubcitemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SettingsScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_screen)
        val prev = findViewById<Button>(R.id.button41)
        prev.setOnClickListener{
            finish()
        }
        val save = findViewById<Button>(R.id.button42)

    }
}