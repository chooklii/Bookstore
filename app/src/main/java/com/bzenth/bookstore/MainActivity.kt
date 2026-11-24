package com.bzenth.bookstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bzenth.bookstore.db.Database
import com.bzenth.bookstore.db.dao.BookRepository
import com.bzenth.bookstore.ui.screens.Application
import com.bzenth.bookstore.ui.theme.BookstoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = BookRepository(Database(applicationContext))


        enableEdgeToEdge()
        setContent {
            BookstoreTheme {
                Application(repository = repository)
            }
        }
    }
}