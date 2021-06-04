package com.makentoshe.androidgithubcitemplate

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val RQ = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonTaps()
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
