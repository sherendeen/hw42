package com.example.week4hw_2

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {
    // aFTER api 23, PERM is requested for accessing external storage
    // before api 23 perm request is asked during installation
    // after api 23 perm request is asked when the app runs
    private val EXTERNAL_STORAGE_PERMISSION_CODE = 23
    lateinit var shared : SharedPreferences
    var editText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            EXTERNAL_STORAGE_PERMISSION_CODE
        )
    }



    fun viewInformation(view: View?) {
        // Creating an intent to start a new activity
        val intent = Intent(this@MainActivity, GetCSVActivity::class.java)
        startActivity(intent)
    }

    fun loadFile(view: View) {



        view.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.MANAGE_EXTERNAL_STORAGE), 0);
                }
            }
            viewInformation(view)
        }

    }
    fun saveFile(view: View) {

        view.setOnClickListener {

        }
    }


}