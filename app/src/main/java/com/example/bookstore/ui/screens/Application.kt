package com.example.bookstore.ui.screens

import com.example.bookstore.R
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import androidx.compose.runtime.mutableStateListOf
import com.example.bookstore.ui.core.Book
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.entryProvider
import com.example.bookstore.ui.components.AddBook
import com.example.bookstore.ui.components.BookList
import com.example.bookstore.ui.components.Footer
import com.example.bookstore.ui.core.NavigationElement

@Composable
fun Application() {

    val backStack = remember { mutableStateListOf<NavKey>(BookList) }
    val books = listOf(Book("Harry Potter",  5, "Harry Potter ist tot"), Book("Star Wars", 2, "", "Bestes Buch!"))

    val currentRoute = backStack.last() as NavigationElement

    Scaffold(
        bottomBar = {
            Footer(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    if (backStack.last() != route) {
                        backStack.add(route)
                    }
                }
            )
        }
    ) { padding ->

        NavDisplay(
            backStack = backStack,
            onBack = {
                if (backStack.size > 1) {
                    backStack.removeLast()
                }
            },
            entryProvider = entryProvider {

                entry<BookList> {
                    BookOverviewScreen(
                        books = books
                    )
                }

                entry<AddBook> {
                    AddBookScreen { _ ->
                        backStack[backStack.lastIndex] = BookList
                    }
                }
            },
            modifier = Modifier.padding(padding)
        )
    }
}