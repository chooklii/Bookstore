package com.bzenth.bookstore.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context?): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase){
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS ${DbConstants.Book.TABLE_NAME} ("
            + " ${DbConstants.Book.ROW_ID} INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " ${DbConstants.Book.NAME} varchar(255) NOT NULL, "
            + " ${DbConstants.Book.RATING} INTEGER NOT NULL, "
            + " ${DbConstants.Book.LEARNING} text, "
            + " ${DbConstants.Book.COMMENT} text "
            + ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int){
        // yagni -> todo
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int){
        // yagni -> todo
    }

    companion object {
        const val DB_NAME = "bookstore.db"
        const val DB_VERSION = 1
    }
}