package com.example.bookstore.ui.screens

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.bookstore.ui.core.Book

val dummyBooks = listOf(
    Book("Harry Potter", 5, "Gut"),
    Book("Star Wars", 2)
)

@Composable
fun BookOverviewScreen() {


    Column {
        Column {
            Text(text = "Book Overview", fontWeight = FontWeight.W700)
        }
        dummyBooks.forEach { book -> DisplaySingleBook(book) }
    }
    }

@Composable
fun DisplaySingleBook(book: Book){
    return(Row(
        verticalAlignment = Alignment.CenterVertically,
        ) {
                Text(text=book.name)
                Text(text=book.rating.toString())

                if(book.comment != null){
                    Text(text=book.comment)
                }


            })
}