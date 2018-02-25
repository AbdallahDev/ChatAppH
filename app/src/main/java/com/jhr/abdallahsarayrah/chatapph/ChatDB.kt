package com.jhr.abdallahsarayrah.chatapph

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by abdallah.sarayrah on 1/22/2018.
 */
class ChatDB(context: Context) : SQLiteOpenHelper(context, "chat.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("create table chat(name string, msg string)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}