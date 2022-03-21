package com.example.mywek42_ver4

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    val textView: TextView = findViewById(R.id.textView)
    private val EXTERNAL_STORAGE_PERMISSION_CODE = 23

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.MANAGE_EXTERNAL_STORAGE),
            EXTERNAL_STORAGE_PERMISSION_CODE
        )

        val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(folder, "100-contacts.csv")
        val db = DBHelper(this, null)
        val inputStream: InputStream = file.inputStream()
        val lines = mutableListOf<String>()
        inputStream.bufferedReader().forEachLine { lines.add(it) }
        //header has column names so remove it
        /*
        first_name,last_name,company_name,address,city,county,state,zip,phone1,phone,email
        , deliminator; 11 columns across, 99 rows (not counting the header)
         */
        lines.removeAt(0)
        lines.forEach{ db.addLine(it) }

        val button : Button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val editText : EditText = findViewById<EditText>(R.id.editText)

            try {
                //
                val num : String = editText.text.toString()
                if (checkFields(num.toInt())) {
                    //for results
                    val textView : TextView = findViewById<TextView>(R.id.textView) as TextView

                    //setup db cursor
                    val cursor = db.getLine(num)
                    cursor!!.moveToFirst()
                    textView.setText(CursorToString.returnString(cursor))

                } else {
                    Toast.makeText(this, "@string/enter_a_number_1_through_100", Toast.LENGTH_LONG)
                }

            } catch (e : IOException) {
                e.printStackTrace()
                Log.e("FILE IO ERROR", "${e.cause} ${e.message} localized as ${e.localizedMessage}")
            } catch (e : Exception) {
                e.printStackTrace()
                Log.e("FILE IO ERROR", "${e.cause} ${e.message} localized as ${e.localizedMessage}")
            }

        }




    }

    private fun checkFields(number:Int) :Boolean{
        // oh
        val regex = Regex("\\b([1-9]|[1-9][0-9])\\b")

        return regex.matches(number.toString())
    }

    fun viewInformation(view: View) {

    }
}