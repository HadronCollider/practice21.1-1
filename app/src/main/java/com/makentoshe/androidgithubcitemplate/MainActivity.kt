package com.makentoshe.androidgithubcitemplate

import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*


class MainActivity : AppCompatActivity() {

    val RQ = 101
    val RQ1 = 228

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val flower16 = findViewById<ImageView>(R.id.imageView)
        val buttonsave = findViewById<Button>(R.id.button2)
        buttonsave.setOnClickListener{
            saveBitmapAsImageToDevice(getBitmapFromView(flower16))
        }


        buttonTaps()
    }


    private fun saveBitmapAsImageToDevice(bitmap: Bitmap?) {
        // Add a specific media item.
        val resolver = this.contentResolver

        val imageStorageAddress = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        val imageDetails = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "my_app_${System.currentTimeMillis()}.jpg")
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.MediaColumns.DATE_ADDED, System.currentTimeMillis())
        }

        try {
            // Save the image.
            val contentUri: Uri? = resolver.insert(imageStorageAddress, imageDetails)
            contentUri?.let { uri ->
                // Don't leave an orphan entry in the MediaStore
                if (bitmap == null) resolver.delete(contentUri, null, null)
                val outputStream: OutputStream? = resolver.openOutputStream(uri)
                outputStream?.let { outStream ->
                    val isBitmapCompressed =
                        bitmap?.compress(Bitmap.CompressFormat.JPEG, 95, outStream)
                    if (isBitmapCompressed == true) {
                        outStream.flush()
                        outStream.close()
                    }
                } ?: throw IOException("Failed to get output stream.")
            } ?: throw IOException("Failed to create new MediaStore record.")
        } catch (e: IOException) {
            throw e
        }
    }
    fun getBitmapFromView(view: View): Bitmap {
        return Bitmap.createBitmap(view.width, view.height,Bitmap.Config.ARGB_8888).apply {
            Canvas(this).apply {
                view.draw(this)
            }
        }
    }



    private fun buttonTaps(){
        button.setOnClickListener {
            checkforPermissions(android.Manifest.permission.READ_EXTERNAL_STORAGE, "read media files", RQ)
            checkforPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, "read media files", RQ)
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
            RQ1 -> innerCheck("write media files")
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
