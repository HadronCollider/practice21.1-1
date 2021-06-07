package com.makentoshe.androidgithubcitemplate

import android.R.attr.*
import android.content.Intent
import android.graphics.*
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
        val seek = findViewById<SeekBar>(R.id.seekBar)
        val photo = findViewById<ImageView>(R.id.image_changing_photo)
        var currentParameter = 'b'
        seek.progress = 128
        var brightnessLevel = 128
        var contrastLevel = 128
        var rotationLevel = 128
        val brightness = findViewById<Button>(R.id.brightness_button)
        val contrast = findViewById<Button>(R.id.contrast_button)
        val rotation = findViewById<Button>(R.id.rotation_button)
        brightness.setOnClickListener{
            Log.d("chosenFeature",currentParameter.toString())
            val whatWasCurrent = currentParameter
            currentParameter = 'b'
            if (whatWasCurrent == 'c'){
                contrastLevel = seek.progress
                seek.progress = brightnessLevel
            }
            if (whatWasCurrent == 'r'){
                rotationLevel = seek.progress
                seek.progress = brightnessLevel
            }
        }

        contrast.setOnClickListener{
            Log.d("chosenFeature",currentParameter.toString())
            val whatWasCurrent = currentParameter
            currentParameter = 'c'
            if (whatWasCurrent == 'b'){
                brightnessLevel = seek.progress
                seek.progress = contrastLevel
            }
            if (whatWasCurrent == 'r'){
                rotationLevel = seek.progress
                seek.progress = contrastLevel
            }
        }

        rotation.setOnClickListener{
            Log.d("chosenFeature",currentParameter.toString())
            val whatWasCurrent = currentParameter
            currentParameter = 'r'
            if (whatWasCurrent == 'b'){
                brightnessLevel = seek.progress
                seek.progress = rotationLevel
            }
            if (whatWasCurrent == 'c'){
                contrastLevel = seek.progress
                seek.progress = rotationLevel
            }
        }
        seek.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (currentParameter == 'b'){
                    photo.colorFilter = changeBrightness(progress)
                }
                if (currentParameter == 'c'){
                }
                if (currentParameter == 'r'){
                    photo.rotation = (progress.toFloat() - 128) * 180 / 128
                    if (progress == 255){
                        photo.rotation = 180.toFloat()
                    }
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }
    fun changeBrightness(progress: Int): PorterDuffColorFilter {
        return if (progress >= 128) {
            val value = (progress - 128) * 255 / 128
            PorterDuffColorFilter(Color.argb(value, 255, 255, 255), PorterDuff.Mode.SRC_OVER)
        } else {
            val value = (128 - progress) * 255 / 128
            PorterDuffColorFilter(Color.argb(value, 0, 0, 0), PorterDuff.Mode.SRC_ATOP)
        }
    }
}