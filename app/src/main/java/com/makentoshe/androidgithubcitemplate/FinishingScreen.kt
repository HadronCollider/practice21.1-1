package com.makentoshe.androidgithubcitemplate

import ThemeHolder
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException


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
        if (intent.hasExtra("path")) {
            val path : String = intent.getStringExtra("path")
            loadImageFromStorage(path)
        }
        val prev = findViewById<Button>(R.id.button51)
        prev.setOnClickListener{
            finish()
        }
        val save = findViewById<Button>(R.id.button52)
    }
    private fun loadImageFromStorage(path: String) {
        try {
            val f = File(path, "profile.jpg")
            Log.d("debug", f.absolutePath);
            val b = BitmapFactory.decodeStream(FileInputStream(f))
            Log.d("debug", "b")
            val img = findViewById<ImageView>(R.id.resultImage)
            Log.d("debug", "img")
            Log.d("debug", b.toString())
            img.setImageBitmap(b)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }
}