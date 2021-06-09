package com.makentoshe.androidgithubcitemplate

import ThemeHolder
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.LayerDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import kotlinx.android.synthetic.main.item_pic.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class WorkingScreen : AppCompatActivity() {
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
        setContentView(R.layout.activity_working_screen)
        val prev = findViewById<Button>(R.id.button31)
        prev.setOnClickListener{
            finish()
        }
        val seek = findViewById<SeekBar>(R.id.seekBar)
        val photo = findViewById<ImageView>(R.id.image_changing_photo)
        if (intent.hasExtra("pictureUri")) {
            val pictureUri = intent.getStringExtra("pictureUri")
            Log.d("pic", pictureUri)
            photo.setImageURI(Uri.fromFile(File(pictureUri)))
        }
        val next = findViewById<Button>(R.id.button32)
        next.setOnClickListener{
            val intentTo5 = Intent(this, FinishingScreen::class.java)
            val bitmap: Bitmap = photo.drawable.toBitmap();
            intentTo5.putExtra("path", saveToInternalStorage(bitmap))
            Log.d("debug", saveToInternalStorage(bitmap))
            startActivity(intentTo5)
        }
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
    private fun saveToInternalStorage(bitmapImage: Bitmap): String? {
        val cw = ContextWrapper(applicationContext)
        val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
        val mypath = File(directory, "profile.jpg")
        Log.d("debug", mypath.absolutePath)
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(mypath)
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fos?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        Log.d("debug", mypath.absolutePath)
        return directory.absolutePath;
    }
}