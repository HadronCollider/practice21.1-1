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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LoadingScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_screen)
        val prev = findViewById<Button>(R.id.button21)
        prev.setOnClickListener{
            finish()
        }
        val next = findViewById<Button>(R.id.button22)
        next.setOnClickListener{
            val intentTo3 = Intent(this, WorkingScreen::class.java)
            startActivity(intentTo3)
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
        rec.adapter = PicAdapter(this, pics)
        rec.setLayoutManager(GridLayoutManager(this, 3))

    }
}


