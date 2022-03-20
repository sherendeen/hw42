package com.example.week4hw_2

import android.Manifest
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.app.ActivityCompat
import java.io.BufferedReader
import java.io.File

class GetCSVActivity : AppCompatActivity() {

    var textView: TextView? = null
    val filename : String = "100-contacts.csv"
    private val EXTERNAL_STORAGE_PERMISSION_CODE = 23

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_csvactivity)
        textView = findViewById<TextView>(R.id.txtResult)

        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            EXTERNAL_STORAGE_PERMISSION_CODE
        )
        textView = this.findViewById(R.id.txtResult)
        showPublicData(this)

    }

    // get specified file
    private fun showPublicData(context: Context) {

        val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        val file = File(
            folder,
            filename
        )
        val data = getData(file)
        if ( data != null) {
            textView!!.text = data
        } else {
            textView!!.text = "'${filename.toString()}' Not Found"
        }

    }

    private fun getData(myFile: File): String? {

        //this code does nothing for me
//        ActivityCompat.requestPermissions(
//            this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
//            EXTERNAL_STORAGE_PERMISSION_CODE
//        )
        var bufferedReader:BufferedReader? = null
        try {
           bufferedReader = myFile.bufferedReader()
        } catch (e : Exception) {
            Log.e("FILE","${e.cause} ${e.message}")
        }
            //read from file
        return bufferedReader.use { it?.readText() }
    }
}