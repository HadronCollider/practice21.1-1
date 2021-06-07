package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity


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
        var currentParameter = 'b'
        val brightness = findViewById<Button>(R.id.brightness_button)
        brightness.setOnClickListener{
            Log.d("chosenFeature",currentParameter.toString())
            currentParameter = 'b'
        }
        val contrast = findViewById<Button>(R.id.contrast_button)
        contrast.setOnClickListener{
            Log.d("chosenFeature",currentParameter.toString())
            currentParameter = 'c'
        }
        val rotation = findViewById<Button>(R.id.rotation_button)
        rotation.setOnClickListener{
            Log.d("chosenFeature",currentParameter.toString())
            currentParameter = 'r'
        }
        val seek = findViewById<SeekBar>(R.id.seekBar)
        val photo = findViewById<ImageView>(R.id.image_changing_photo)
        seek.progress = 128
        seek.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (currentParameter == 'b'){
                    photo.colorFilter = changeBrightness(progress)
                }
                if (currentParameter == 'c'){
                }
                if (currentParameter == 'r'){
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

    }
    fun changeBrightness(progress: Int): PorterDuffColorFilter {
        return if (progress >= 100) {
            val value = (progress - 100) * 255 / 100
            PorterDuffColorFilter(Color.argb(value, 255, 255, 255), PorterDuff.Mode.SRC_OVER)
        } else {
            val value = (100 - progress) * 255 / 100
            PorterDuffColorFilter(Color.argb(value, 0, 0, 0), PorterDuff.Mode.SRC_ATOP)
        }
    }
}