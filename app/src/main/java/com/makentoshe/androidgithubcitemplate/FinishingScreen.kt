package com.makentoshe.androidgithubcitemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FinishingScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (ThemeHolder.INSTANCE.realTheme == 'w'){
            setTheme(R.style.AppTheme)
        }
        if (ThemeHolder.INSTANCE.realTheme == 'd'){
            setTheme(R.style.DarkTheme)
        }
        if (ThemeHolder.INSTANCE.realTheme == 'b'){
            setTheme(R.style.BeachTheme)
        }
        if (ThemeHolder.INSTANCE.realTheme == 'g'){
            setTheme(R.style.GreenTheme)
        }
        setContentView(R.layout.activity_finishing_screen)
        val prev = findViewById<Button>(R.id.button51)
        prev.setOnClickListener{
            finish()
        }
        val save = findViewById<Button>(R.id.button52)
    }
}