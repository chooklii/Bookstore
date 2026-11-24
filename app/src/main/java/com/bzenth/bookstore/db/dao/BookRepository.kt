package com.bzenth.bookstore.db.dao

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import androidx.core.database.getStringOrNull
import com.bzenth.bookstore.db.Database
import com.bzenth.bookstore.db.DbConstants
import com.bzenth.bookstore.db.core.Book


class BookRepository(db: Database) {

    val readDB: SQLiteDatabase = db.readableDatabase;
    val writeDB: SQLiteDatabase = db.writableDatabase;

    fun insertBook(book: Book): Boolean{

        val values = ContentValues().apply {
            put(DbConstants.Book.NAME, book.name)
            put(DbConstants.Book.RATING, book.rating)
            put(DbConstants.Book.LEARNING, book.learning)
            put(DbConstants.Book.COMMENT, book.comment)
        }

        val insert = writeDB.insert(DbConstants.Book.TABLE_NAME, null, values);

        // returns -1 on Error
        return insert.toInt() != -1;
    }

    fun editBook(book: Book): Boolean{

        val values = ContentValues().apply {
            put(DbConstants.Book.NAME, book.name)
            put(DbConstants.Book.RATING, book.rating)
            put(DbConstants.Book.LEARNING, book.learning)
            put(DbConstants.Book.COMMENT, book.comment)
        }

        val update = writeDB.update(
            DbConstants.Book.TABLE_NAME,
            values,
            "${DbConstants.Book.ROW_ID} = ?",
            arrayOf(book.rowId.toString()))

        // returns count of updated rows
        return update > 0
    }

    fun getAllBooksByQuery(query: String): List<Book>{
        Log.i("BookRepository", "Query Books with Query String: $query")

        val books = mutableListOf<Book>();

        val selection = if(query.isNotEmpty()) "${DbConstants.Book.NAME} LIKE ?" else null;
        val selectionArgs = if(query.isNotEmpty()) arrayOf("%$query%") else null


        val cursor = readDB.query(
            DbConstants.Book.TABLE_NAME,
            arrayOf(DbConstants.Book.ROW_ID, DbConstants.Book.NAME, DbConstants.Book.RATING,
                DbConstants.Book.LEARNING, DbConstants.Book.COMMENT),
            selection,
            selectionArgs,
            null,
            null,
            null,
            null)

        cursor.use{
            while(cursor.moveToNext()){
                books.add(Book(
                    cursor.getInt( cursor.getColumnIndexOrThrow(DbConstants.Book.ROW_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.Book.NAME)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.Book.RATING)),
                    cursor.getStringOrNull(cursor.getColumnIndexOrThrow(DbConstants.Book.LEARNING)),
                    cursor.getStringOrNull(cursor.getColumnIndexOrThrow(DbConstants.Book.COMMENT))
                ))
            }
        }
        return books;
    }
}