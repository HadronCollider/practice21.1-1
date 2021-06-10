package com.makentoshe.androidgithubcitemplate

import ThemeHolder
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import kotlinx.android.synthetic.main.activity_working_screen.*
import java.io.ByteArrayOutputStream
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
            photo.setDrawingCacheEnabled(true)
            photo.buildDrawingCache()
            val bm: Bitmap = photo.getDrawingCache()
            val baos = ByteArrayOutputStream()
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            intentTo5.putExtra("path", saveToInternalStorage(bm))
            Log.d("debug", saveToInternalStorage(bm))
            startActivity(intentTo5)
        }
        var chosenFilter = 'w'
        var currentParameter = 'b'
        seek.progress = 64
        var brightnessLevel = 64
        var redlevel = 0
        var bluelevel = 0
        var greenlevel = 0
        val brightness = findViewById<ImageButton>(R.id.brightness_button)
        val greenFilterButton = findViewById<Button>(R.id.greenButton)
        val blueFilterButton = findViewById<Button>(R.id.blueButton)
        val redFilterButton = findViewById<Button>(R.id.redButton)
        val resetButton = findViewById<ImageButton>(R.id.resetButton)
        greenFilterButton.setOnClickListener{
            redButton.setBackgroundDrawable(getResources().getDrawable(R.color.red))
            greenButton.setBackgroundDrawable(getResources().getDrawable(R.color.green))
            blueButton.setBackgroundDrawable(getResources().getDrawable(R.color.blue))
            brightness_button.setBackgroundDrawable(getResources().getDrawable(R.color.grey))
            val whatWasCurrent = currentParameter
            currentParameter = 'c'
            if (whatWasCurrent == 'b'){
                brightnessLevel = seek.progress
            }
            if (whatWasCurrent == 'c'){
                if (chosenFilter == 'b'){
                    bluelevel = seek.progress
                }
                if (chosenFilter == 'r'){
                    redlevel = seek.progress
                }
            }
            chosenFilter = 'g'
            seek.progress = greenlevel

        }
        redFilterButton.setOnClickListener{
            redButton.setBackgroundDrawable(getResources().getDrawable(R.color.pressed_red))
            greenButton.setBackgroundDrawable(getResources().getDrawable(R.color.button_green))
            blueButton.setBackgroundDrawable(getResources().getDrawable(R.color.blue))
            brightness_button.setBackgroundDrawable(getResources().getDrawable(R.color.grey))
            val whatWasCurrent = currentParameter
            currentParameter = 'c'
            if (whatWasCurrent == 'b'){
                brightnessLevel = seek.progress
            }
            if (whatWasCurrent == 'c'){
                if (chosenFilter == 'g'){
                    greenlevel = seek.progress
                }
                if (chosenFilter == 'b'){
                    bluelevel = seek.progress
                }
            }
            chosenFilter = 'r'
            seek.progress = redlevel

        }
        blueFilterButton.setOnClickListener{
            redButton.setBackgroundDrawable(getResources().getDrawable(R.color.red))
            greenButton.setBackgroundDrawable(getResources().getDrawable(R.color.button_green))
            blueButton.setBackgroundDrawable(getResources().getDrawable(R.color.pressed_blue))
            brightness_button.setBackgroundDrawable(getResources().getDrawable(R.color.grey))
            val whatWasCurrent = currentParameter
            currentParameter = 'c'
            if (whatWasCurrent == 'b'){
                brightnessLevel = seek.progress
            }
            if (whatWasCurrent == 'c'){
                if (chosenFilter == 'g'){
                    greenlevel = seek.progress
                }
                if (chosenFilter == 'r'){
                    redlevel = seek.progress
                }
            }
            chosenFilter = 'b'
            seek.progress = bluelevel

        }
        resetButton.setOnClickListener{
            redButton.setBackgroundDrawable(getResources().getDrawable(R.color.red))
            greenButton.setBackgroundDrawable(getResources().getDrawable(R.color.button_green))
            blueButton.setBackgroundDrawable(getResources().getDrawable(R.color.blue))
            brightness_button.setBackgroundDrawable(getResources().getDrawable(R.color.grey))
            redlevel = 0
            bluelevel = 0
            greenlevel = 0
            brightnessLevel = 64
            seek.progress = 64
            chosenFilter = 'w'
            val source = photo.drawable
            val colorTransform = floatArrayOf(
                brightnessLevel.toFloat()/ 64, redlevel.toFloat() / 256, 0f, 0f, 0f,
                0f, brightnessLevel.toFloat()/ 64, greenlevel.toFloat() / 256, 0f, 0f,
                0f, 0f, brightnessLevel.toFloat()/ 64, bluelevel.toFloat() / 256, 0f,
                0f, 0f, 0f, 1f, 0f
            )
            Log.d("k", brightnessLevel.toString())
            val colorMatrix = ColorMatrix()
            colorMatrix.set(colorTransform)
            val colorFilter = ColorMatrixColorFilter(colorMatrix)
            source.colorFilter = colorFilter
            photo.setImageDrawable(source)
        }
        brightness.setOnClickListener{
            redButton.setBackgroundDrawable(getResources().getDrawable(R.color.red))
            greenButton.setBackgroundDrawable(getResources().getDrawable(R.color.button_green))
            blueButton.setBackgroundDrawable(getResources().getDrawable(R.color.blue))
            brightness_button.setBackgroundDrawable(getResources().getDrawable(R.color.grey_pressed))
            val whatWasCurrent = currentParameter
            currentParameter = 'b'
            if (whatWasCurrent == 'c'){
                if (chosenFilter == 'g'){
                    greenlevel = seek.progress
                }
                if (chosenFilter == 'r'){
                    redlevel = seek.progress
                }
                if (chosenFilter == 'b'){
                    bluelevel = seek.progress
                }
                seek.progress = brightnessLevel
            }
        }
        seek.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (currentParameter == 'b') {
                    val source = photo.drawable
                    val colorTransform = floatArrayOf(
                        progress.toFloat()/ 64, redlevel.toFloat() / 256, 0f, 0f, 0f,
                        0f, progress.toFloat()/ 64, greenlevel.toFloat() / 256, 0f, 0f,
                        0f, 0f, progress.toFloat()/ 64, bluelevel.toFloat() / 256, 0f,
                        0f, 0f, 0f, 1f, 0f
                    )
                    Log.d("k", progress.toString())
                    val colorMatrix = ColorMatrix()
                    colorMatrix.set(colorTransform)
                    val colorFilter = ColorMatrixColorFilter(colorMatrix)
                    source.colorFilter = colorFilter
                    photo.setImageDrawable(source)
                }
                if (currentParameter == 'c') {
                    val source = photo.drawable
                    var colorTransform = floatArrayOf(
                        brightnessLevel.toFloat()/ 64, 0f, 0f, 0f, 0f,
                        0f, brightnessLevel.toFloat()/ 64, 0f, 0f, 0f,
                        0f, 0f, brightnessLevel.toFloat()/ 64, 0f, 0f,
                        0f, 0f, 0f, 1f, 0f
                    )
                    if (chosenFilter == 'r') {
                        colorTransform = floatArrayOf(
                            brightnessLevel.toFloat()/ 64, progress.toFloat() / 256, 0f, 0f, 0f,
                            0f, brightnessLevel.toFloat()/ 64, greenlevel.toFloat() / 256, 0f, 0f,
                            0f, 0f, brightnessLevel.toFloat()/ 64, bluelevel.toFloat() / 256, 0f,
                            0f, 0f, 0f, 1f, 0f
                        )
                    }
                    if (chosenFilter == 'g') {
                        colorTransform = floatArrayOf(
                            brightnessLevel.toFloat() / 64, redlevel.toFloat() / 256, 0f, 0f, 0f,
                            0f, brightnessLevel.toFloat()/ 64, progress.toFloat() / 256, 0f, 0f,
                            0f, 0f, brightnessLevel.toFloat() / 64, bluelevel.toFloat() / 256, 0f,
                            0f, 0f, 0f, 1f, 0f
                        )
                    }
                    if (chosenFilter == 'b') {
                        colorTransform = floatArrayOf(
                            brightnessLevel.toFloat()/ 64, redlevel.toFloat() / 256, 0f, 0f, 0f,
                            0f, brightnessLevel.toFloat() / 64, greenlevel.toFloat() / 256, 0f, 0f,
                            0f, 0f, brightnessLevel.toFloat()/ 64, progress.toFloat() / 256, 0f,
                            0f, 0f, 0f, 1f, 0f
                        )
                    }
                    Log.d("k", progress.toString())
                    val colorMatrix = ColorMatrix()
                    colorMatrix.set(colorTransform)
                    val colorFilter = ColorMatrixColorFilter(colorMatrix)
                    source.colorFilter = colorFilter
                    photo.setImageDrawable(source)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
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