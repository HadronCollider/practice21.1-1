package com.makentoshe.androidgithubcitemplate

import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    val RQ = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonTaps()

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
        val b2 = findViewById<Button>(R.id.button2)
        var cur: Int = 0;
        val photos = getCameraImages();
        b2.setOnClickListener {
            val g = findViewById<ImageView>(R.id.imageView);
            g.setImageURI(Uri.fromFile(File(photos[cur])))
            cur = (cur + 1) % photos.size;
        }
    }

    private fun buttonTaps(){
        button.setOnClickListener {
            checkforPermissions(android.Manifest.permission.READ_EXTERNAL_STORAGE, "read media files", RQ)
        }

    }

    private fun checkforPermissions(permission:String, name:String,requestCode: Int){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when {
                ContextCompat.checkSelfPermission(applicationContext,permission) == PackageManager.PERMISSION_GRANTED -> {
                    Toast.makeText(applicationContext, "$name permission granted", Toast.LENGTH_SHORT ).show()
                }
                shouldShowRequestPermissionRationale(permission) -> showDialog(permission,name,requestCode)

                else -> ActivityCompat.requestPermissions(this, arrayOf(permission),requestCode)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
       fun innerCheck(name: String){
           if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
               Toast.makeText(applicationContext,"$name permission refused", Toast.LENGTH_SHORT).show()
           } else {
               Toast.makeText(applicationContext,"$name permission granted", Toast.LENGTH_SHORT).show()
           }
       }

        when (requestCode){
            RQ -> innerCheck("read media files")

        }
    }

    private fun showDialog(permission:String, name:String,requestCode: Int){
        val builder =AlertDialog.Builder(this)

        builder.apply {
            setMessage("Permission to access your $name is requared to use this app")
            setTitle("Permission required")
            setPositiveButton("Ok"){dialog, which ->
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission),requestCode)
            }
        }
        val dialog = builder.create()
        dialog.show()
    }
}
