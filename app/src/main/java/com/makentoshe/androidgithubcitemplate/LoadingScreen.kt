package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LoadingScreen : AppCompatActivity() {
    var pictureUri = "sus";
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
        setContentView(R.layout.activity_loading_screen)
        val prev = findViewById<Button>(R.id.button21)
        prev.setOnClickListener{
            finish()
        }
        val next = findViewById<Button>(R.id.button22)
        next.setOnClickListener {
            if (pictureUri == "sus") {
                Toast.makeText(applicationContext, "Выберите фотографию!", Toast.LENGTH_SHORT).show()
            } else {
                val intentTo3 = Intent(this, WorkingScreen::class.java)
                intentTo3.putExtra("pictureUri", pictureUri)
                startActivity(intentTo3)
            }
        }
        fun getCameraImages(): List<String> {
            val uri: Uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val cursor: Cursor?
            val listOfAllImages = ArrayList<String>()
            var absolutePathOfImage: String? = null

            val projection = arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

            cursor = contentResolver.query(uri, projection, null,
                null, null);

            val columnIndexData: Int = cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            val columnIndexFolderName = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            while (cursor.moveToNext()) {
                absolutePathOfImage = cursor.getString(columnIndexData)
                listOfAllImages.add(absolutePathOfImage)
            }
            return listOfAllImages
        }
        val photos: List<String> = getCameraImages()
        val pics = mutableListOf<Pic>();
        for (x in photos){
            pics.add(Pic(x))
        }
        val rec = findViewById<RecyclerView>(R.id.Recycler)
        rec.adapter = PicAdapter(pics, this)
        rec.setLayoutManager(GridLayoutManager(this, 3))
    }
    public fun setPicture (s : String) {
        pictureUri = s
    }
}


