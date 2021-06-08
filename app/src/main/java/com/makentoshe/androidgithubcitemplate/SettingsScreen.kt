package com.makentoshe.androidgithubcitemplate

import ThemeHolder
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class SettingsScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (ThemeHolder.INSTANCE.chosenTheme == 'w'){
            setTheme(R.style.AppTheme)
        }
        if (ThemeHolder.INSTANCE.chosenTheme == 'd'){
            setTheme(R.style.DarkTheme)
        }
        if (ThemeHolder.INSTANCE.chosenTheme == 'b'){
            setTheme(R.style.BeachTheme)
        }
        if (ThemeHolder.INSTANCE.chosenTheme == 'g'){
            setTheme(R.style.GreenTheme)
        }
        setContentView(R.layout.activity_settings_screen)
        val prev = findViewById<Button>(R.id.button41)
        prev.setOnClickListener{
            ThemeHolder.INSTANCE.changeTheme(ThemeHolder.INSTANCE.realTheme)
            finish()
        }
        val save = findViewById<Button>(R.id.button42)
        val white = findViewById<Button>(R.id.white_button)
        val black = findViewById<Button>(R.id.black_button)
        val beach = findViewById<Button>(R.id.beach_button)
        val green = findViewById<Button>(R.id.green_button)
        white.setOnClickListener{
            ThemeHolder.INSTANCE.changeTheme('w')
            recreate()
        }
        black.setOnClickListener{
            ThemeHolder.INSTANCE.changeTheme('d')
            recreate()
        }
        beach.setOnClickListener{
            ThemeHolder.INSTANCE.changeTheme('b')
            recreate()
        }
        green.setOnClickListener{
            ThemeHolder.INSTANCE.changeTheme('g')
            recreate()
        }
        save.setOnClickListener{
            ThemeHolder.INSTANCE.changeRealTheme(ThemeHolder.INSTANCE.chosenTheme)
            ThemeHolder.INSTANCE.mainNeedsToBeChanged = true
            finish()
        }
    }
}