package com.example.mywek42_ver4

import android.annotation.SuppressLint
import android.database.Cursor
import com.example.mywek42_ver4.DBHelper.Companion.address
import com.example.mywek42_ver4.DBHelper.Companion.company_name
import com.example.mywek42_ver4.DBHelper.Companion.first_name
import com.example.mywek42_ver4.DBHelper.Companion.last_name

class CursorToString {
    companion object{
        @SuppressLint("Range")
        fun  returnString(cursor: Cursor):String{
            val a = "Name : " + (cursor.getString(cursor.getColumnIndex(first_name))) + " " + (cursor.getString(cursor.getColumnIndex(last_name )))
            val b = "Company : " + (cursor.getString(cursor.getColumnIndex(company_name)))
            val c = "Address : " + (cursor.getString(cursor.getColumnIndex(address)))

            return "$a $b $c"
        }
    }
}