package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
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
        seek.progress = 128
        seek.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                photo.colorFilter = changeBrightness(progress)
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